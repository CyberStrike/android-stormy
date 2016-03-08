package cyberstrike.co.stormy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by chris on 7/2/15.
 */
public class AlertDialogFragment extends DialogFragment {

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    Context context = getActivity();
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setTitle("Ooops, Sorry!")
        .setMessage("There was an error, please try again.")
        .setPositiveButton("OK", null);

    AlertDialog dialog = builder.create();

    return dialog;
  }
}
