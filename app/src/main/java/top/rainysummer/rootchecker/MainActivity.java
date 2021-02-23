package top.rainysummer.rootchecker;

import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.lahm.library.EasyProtectorLib;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean isRoot = EasyProtectorLib.checkIsRoot();
        boolean isXposed = EasyProtectorLib.checkIsXposedExist();
        boolean isEmulator = EasyProtectorLib.checkIsRunningInEmulator(this, emulatorInfo -> {
        });

        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        if (isRoot) {
            imageView.setImageResource(R.drawable.seele);
        } else if (isXposed) {
            imageView.setImageResource(R.drawable.ex);
        } else if (isEmulator) {
            imageView.setImageResource(R.drawable.phone);
        } else {
            imageView.setImageResource(R.drawable.yes);
        }

        TextView txtRoot = (TextView) findViewById(R.id.txtRoot);
        TextView txtXposed = (TextView) findViewById(R.id.txtXposed);
        TextView txtEmulator = (TextView) findViewById(R.id.txtEmulator);
        String strRoot, strXposed, strEmulator;
        if (isRoot) {
            strRoot = "\u274E <b><tt>Root</tt></b>: <font color=\"#FF4444\">Rooted</font>";
        } else {
            strRoot = "\u2705 <b><tt>Root</tt></b>: <font color=\"#99CC00\">Not Rooted</font>";
        }
        if (isXposed) {
            strXposed = "\u274E <b><tt>Xposed</tt></b>: <font color=\"#FF4444\">Xposed Detected</font>";
        } else {
            strXposed = "\u2705 <b><tt>Xposed</tt></b>: <font color=\"#99CC00\">Xposed Not Detected</font>";
        }
        if (isEmulator) {
            strEmulator = "\u274E <b><tt>Emulator</tt></b>: <font color=\"#FF4444\">Emulator Detected</font>";
        } else {
            strEmulator = "\u2705 <b><tt>Emulator</tt></b>: <font color=\"#99CC00\">Not in Emulator Environment</font>";
        }
        txtRoot.setText(Html.fromHtml(strRoot));
        txtXposed.setText(Html.fromHtml(strXposed));
        txtEmulator.setText(Html.fromHtml(strEmulator));

        TextView txtStatus = (TextView) findViewById(R.id.txtStatus);
        if (isRoot || isXposed || isEmulator) {
            txtStatus.setText(Html.fromHtml("<font color=\"#FF4444\">Failed!</font>"));
        } else {
            txtStatus.setText(Html.fromHtml("<font color=\"#99CC00\">Passed!</font>"));
        }
    }
}