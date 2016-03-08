package cyberstrike.co.stormy;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class MainActivity extends Activity {

  public static final String TAG = MainActivity.class.getSimpleName();

  private ImageView mImageView;

  private CurrentWeather mCurrentWeather;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    double latitude = 37.8267;
    double longitude = -122.43;
    String forecastIoKey = getString(R.string.forecastIoKey);
    String forecastUrl = "https://api.forecast.io/forecast/" + forecastIoKey + "/" +
        latitude + "," + longitude;

    if (isNetworkAvailable()){
      OkHttpClient client = new OkHttpClient();
      Request request = new Request.Builder()
          .url(forecastUrl)
          .build();

      Call call = client.newCall(request);
      call.enqueue(new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {

        }

        @Override
        public void onResponse(Response response) throws IOException {
          try {
            String mJSON  = response.body().string();
            Log.v(TAG, mJSON);

            if (response.isSuccessful()) {
              mCurrentWeather = getCurrentDetails(mJSON);

            } else {
              alertUser(response);
            }
          } catch (IOException | JSONException e) {
            Log.e(TAG, "Exception Caught: ", e);
          }

        }
      });
    }
    else{
      Toast.makeText(this, "Network is unavailable.", Toast.LENGTH_LONG).show();
    }

  }

  private CurrentWeather getCurrentDetails(String mJSON) throws JSONException {
    JSONObject forecast = new JSONObject(mJSON);
    JSONObject currently = forecast.getJSONObject("currently");

    CurrentWeather currentWeather = new CurrentWeather();
    currentWeather.setTime(currently.getLong("time"));
    currentWeather.setIcon(currently.getString("icon"));
    currentWeather.setSummary(currently.getString("summary"));
    currentWeather.setTimeZone(forecast.getString("timezone"));
    currentWeather.setHumidity(currently.getDouble("humidity"));
    currentWeather.setTemperature(currently.getDouble("temperature"));
    currentWeather.setPrecipitationChance(currently.getDouble("precipProbability"));

    Log.i(TAG, "JSON: " + currentWeather);


    return currentWeather;
  }

  private boolean isNetworkAvailable() {

    ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo = manager.getActiveNetworkInfo();
    boolean isAvailable = false;

    if(networkInfo != null && networkInfo.isConnected()){
      isAvailable = true;
    }

    return isAvailable;
  }

  private void alertUser(Response response) {

    AlertDialogFragment dialog = new AlertDialogFragment();
    dialog.show(getFragmentManager(), "error_dialog");

  }
}
