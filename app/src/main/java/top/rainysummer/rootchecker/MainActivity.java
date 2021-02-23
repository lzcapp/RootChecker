package top.rainysummer.rootchecker;

import android.os.Bundle;
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

        TextView textView = (TextView) findViewById(R.id.textView);
        String strRoot = "", strXposed = "", strEmulator = "";
        if (isRoot) {
            strRoot = "\u274E Root: Rooted";
        } else {
            strRoot = "\u2705 Root: Not Rooted";
        }
        if (isXposed) {
            strXposed = "\u274E Xposed: Xposed Detected";
        } else {
            strXposed = "\u2705 Xposed: Xposed Not Detected";
        }
        if (isEmulator) {
            strEmulator = "\u274E Emulator: Emulator Detected";
        } else {
            strEmulator = "\u2705 Emulator: Not in Emulator Environment";
        }
        textView.setText(strRoot + "\n" + strXposed + "\n" + strEmulator);
    }
}