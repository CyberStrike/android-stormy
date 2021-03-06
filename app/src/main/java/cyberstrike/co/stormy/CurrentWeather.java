package cyberstrike.co.stormy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by chris on 7/2/15.
 */
public class CurrentWeather {
  private long mTime;
  private String mIcon;
  private String mSummary;
  private String mTimeZone;
  private double mHumidity;
  private double mTemperature;
  private double mPrecipitationChance;

  public long getTime() {
    return mTime;
  }

  public String getFormattedTime(){

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a");
    simpleDateFormat.setTimeZone(TimeZone.getTimeZone(getTimeZone()));

    Date dateTime = new Date(getTime() * 1000);
    String timeString = simpleDateFormat.format(dateTime);

    return timeString;

  }

  public void setTime(long time) {
    mTime = time;
  }

  public String getTimeZone() {
    return mTimeZone;
  }

  public void setTimeZone(String timeZone) {
    mTimeZone = timeZone;
  }

  public String getIcon() {
    return mIcon;
  }

  public int getIconId(){

    int iconId = R.drawable.clear_day;

    if (mIcon.equals("clear-day")) {
      iconId = R.drawable.clear_day;
    }
    else if (mIcon.equals("clear-night")) {
      iconId = R.drawable.clear_night;
    }
    else if (mIcon.equals("rain")) {
      iconId = R.drawable.rain;
    }
    else if (mIcon.equals("snow")) {
      iconId = R.drawable.snow;
    }
    else if (mIcon.equals("sleet")) {
      iconId = R.drawable.sleet;
    }
    else if (mIcon.equals("wind")) {
      iconId = R.drawable.wind;
    }
    else if (mIcon.equals("fog")) {
      iconId = R.drawable.fog;
    }
    else if (mIcon.equals("cloudy")) {
      iconId = R.drawable.cloudy;
    }
    else if (mIcon.equals("partly-cloudy-day")) {
      iconId = R.drawable.partly_cloudy;
    }
    else if (mIcon.equals("partly-cloudy-night")) {
      iconId = R.drawable.cloudy_night;
    }

    return iconId;

  }

  public void setIcon(String icon) {
    mIcon = icon;
  }

  public String getSummary() {
    return mSummary;
  }

  public void setSummary(String summary) {
    mSummary = summary;
  }

  public double getHumidity() {
    return mHumidity;
  }

  public void setHumidity(double humidity) {
    mHumidity = humidity;
  }

  public double getTemperature() {
    return mTemperature;
  }

  public void setTemperature(double temperature) {
    mTemperature = temperature;
  }

  public double getPrecipitationChance() {
    return mPrecipitationChance;
  }

  public void setPrecipitationChance(double precipitationChance) {
    mPrecipitationChance = precipitationChance;
  }

}
