package net.mobctrl.callendview;

import net.mobctrl.callendview.CallSliderEndView.SliderEndListener;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

/**
 * 
 * @author Zheng Haibo
 * @web http://www.mobctrl.net
 *
 */
public class MainActivity extends Activity {

	private CallSliderEndView callSliderEndView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		callSliderEndView = (CallSliderEndView) findViewById(R.id.csev_slider_view);
		callSliderEndView.setSliderEndListener(new SliderEndListener() {

			@Override
			public void onSliderEnd() {
				System.out.println("debug:onSliderEnd...");
				Toast.makeText(getApplicationContext(), "Slider To End",
						Toast.LENGTH_SHORT).show();
			}
		});
	}

}
