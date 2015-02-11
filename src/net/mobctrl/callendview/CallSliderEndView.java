package net.mobctrl.callendview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @date 2015年2月2日 下午11:02:00
 * @author Zheng Haibo
 * @Description: 滑动挂断的自定义控件
 * @Web http://www.mobctrl.net
 */
@SuppressLint({ "DrawAllocation", "ClickableViewAccessibility" })
public class CallSliderEndView extends View {

	public interface SliderEndListener {
		public void onSliderEnd();
	}

	private SliderEndListener sliderEndListener;

	public void setSliderEndListener(SliderEndListener sliderEndListener) {
		this.sliderEndListener = sliderEndListener;
	}

	private int height;
	private int width;
	private int circleOffset = 0;
	private int prevX = 0;
	private int maxOffset;
	private String sliderText;
	private float textSize;
	private int progressBackgroundColor;
	private int backgroundColor;
	private int redReginWidth;

	public CallSliderEndView(Context context) {
		super(context);
		init(context, null);
	}

	public CallSliderEndView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public CallSliderEndView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs) {
		if (null == attrs) {
			return;
		}
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.CallSliderEndView);
		textSize = typedArray.getDimensionPixelSize(
				R.styleable.CallSliderEndView_textSize, 40);
		sliderText = typedArray.getString(R.styleable.CallSliderEndView_text);
		progressBackgroundColor = typedArray.getColor(
				R.styleable.CallSliderEndView_progressBackgroundColor,
				Color.GREEN);
		backgroundColor = typedArray.getColor(
				R.styleable.CallSliderEndView_backgroundColor, 0x0fffffff);
		typedArray.recycle();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// TODO
		height = getHeight();
		width = getWidth();
		// 绘制背景
		Paint paint = new Paint();
		paint.setStyle(Style.FILL);
		paint.setAntiAlias(true);
		paint.setColor(backgroundColor);
		drawBackground(canvas, paint);
		// drawCircleButton(canvas, paint);
		drawRoundButton(canvas, paint);
	}

	// 绘制背景
	private void drawBackground(Canvas canvas, Paint paint) {
		canvas.drawRoundRect(new RectF(0, 0, width, height), height / 2,
				height / 2, paint);
	}

	// 绘制挂断按钮的View
	@Deprecated
	private void drawCircleButton(Canvas canvas, Paint paint) {
		int circleMargin = height / 10;
		paint.setColor(Color.RED);
		canvas.drawCircle(height / 2 + circleOffset, height / 2, height / 2
				- circleMargin, paint);
	}

	// 绘制挂断按钮的View
	private void drawRoundButton(Canvas canvas, Paint paint) {
		redReginWidth = width / 2;

		// 绘制进度背景
		paint.setColor(progressBackgroundColor);
		canvas.drawRoundRect(new RectF(circleOffset, 0, circleOffset
				+ redReginWidth, height), height / 2, height / 2, paint);

		// 将文本sliderText显示在中间
		paint.setTextSize(textSize);
		paint.setColor(Color.WHITE);
		int yCenterPos = (int) ((canvas.getHeight() / 2) - ((paint.descent() + paint
				.ascent()) / 2));// 计算在Y
		int startX = circleOffset
				+ (redReginWidth - (int) paint.measureText(sliderText, 0,
						sliderText.length())) / 2;
		canvas.drawText(sliderText, startX, yCenterPos, paint);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			actionDown(event);
			break;
		case MotionEvent.ACTION_MOVE:
			actionMove(event);
			break;
		case MotionEvent.ACTION_UP:
			actionUp(event);
			break;
		}
		return true;
	}

	private void actionUp(MotionEvent event) {
		if (this.circleOffset != maxOffset) {
			this.circleOffset = 0;
		}
		postInvalidate();
	}

	private void actionMove(MotionEvent event) {
		int tempOffset = (int) (event.getX() - this.prevX);
		this.maxOffset = width - redReginWidth;
		if (tempOffset >= maxOffset && this.circleOffset == maxOffset) {
			return;
		}
		this.circleOffset = tempOffset;
		if (this.circleOffset > maxOffset) {// 是否已经滑动到边缘
			this.circleOffset = maxOffset;
			if (sliderEndListener != null) {
				sliderEndListener.onSliderEnd();
			}
		}
		if (this.circleOffset <= 0) {
			this.circleOffset = 0;
		}
		postInvalidate();
	}

	private void actionDown(MotionEvent e) {
		this.prevX = (int) e.getX();
	}
}
