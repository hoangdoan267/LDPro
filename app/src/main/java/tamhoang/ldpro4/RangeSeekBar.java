package tamhoang.ldpro4;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import java.math.BigDecimal;

public class RangeSeekBar<T extends Number> extends ImageView {
    public static final int ACTION_POINTER_INDEX_MASK = 65280;

    public static final int ACTION_POINTER_INDEX_SHIFT = 8;

    public static final int ACTION_POINTER_UP = 6;

    public static final int DEFAULT_COLOR;

    public static final Integer DEFAULT_MAXIMUM;

    public static final Integer DEFAULT_MINIMUM = Integer.valueOf(0);

    private static final int DEFAULT_TEXT_DISTANCE_TO_BUTTON_IN_DP = 8;

    private static final int DEFAULT_TEXT_DISTANCE_TO_TOP_IN_DP = 8;

    private static final int DEFAULT_TEXT_SIZE_IN_DP = 14;

    public static final int HEIGHT_IN_DP = 30;

    private static final int INITIAL_PADDING_IN_DP = 8;

    public static final int INVALID_POINTER_ID = 255;

    public static final int TEXT_LATERAL_PADDING_IN_DP = 3;

    private float INITIAL_PADDING;

    private final int LINE_HEIGHT_IN_DP = 1;

    private T absoluteMaxValue;

    private double absoluteMaxValuePrim;

    private T absoluteMinValue;

    private double absoluteMinValuePrim;

    private OnRangeSeekBarChangeListener<T> listener;

    private int mActivePointerId;

    private int mDistanceToTop;

    private float mDownMotionX;

    private boolean mIsDragging;

    private RectF mRect;

    private int mScaledTouchSlop;

    private boolean mSingleThumb;

    private int mTextOffset;

    private int mTextSize;

    private double normalizedMaxValue;

    private double normalizedMinValue;

    private boolean notifyWhileDragging;

    private NumberType numberType;

    private float padding;

    private final Paint paint = new Paint(1);

    private Thumb pressedThumb;

    private final Bitmap thumbDisabledImage = BitmapFactory.decodeResource(getResources(), 2131165338);

    private final float thumbHalfHeight;

    private final float thumbHalfWidth;

    private final Bitmap thumbImage = BitmapFactory.decodeResource(getResources(), 2131165339);

    private final Bitmap thumbPressedImage = BitmapFactory.decodeResource(getResources(), 2131165340);

    private final float thumbWidth;

    static {
        DEFAULT_MAXIMUM = Integer.valueOf(100);
        DEFAULT_COLOR = Color.argb(255, 51, 181, 229);
    }

    public RangeSeekBar(Context paramContext) {
        super(paramContext);
        float f = this.thumbImage.getWidth();
        this.thumbWidth = f;
        this.thumbHalfWidth = f * 0.5F;
        this.thumbHalfHeight = this.thumbImage.getHeight() * 0.5F;
        this.normalizedMinValue = 0.0D;
        this.normalizedMaxValue = 1.0D;
        this.pressedThumb = null;
        this.notifyWhileDragging = false;
        this.mActivePointerId = 255;
        init(paramContext, (AttributeSet)null);
    }

    public RangeSeekBar(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        float f = this.thumbImage.getWidth();
        this.thumbWidth = f;
        this.thumbHalfWidth = f * 0.5F;
        this.thumbHalfHeight = this.thumbImage.getHeight() * 0.5F;
        this.normalizedMinValue = 0.0D;
        this.normalizedMaxValue = 1.0D;
        this.pressedThumb = null;
        this.notifyWhileDragging = false;
        this.mActivePointerId = 255;
        init(paramContext, paramAttributeSet);
    }

    public RangeSeekBar(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        float f = this.thumbImage.getWidth();
        this.thumbWidth = f;
        this.thumbHalfWidth = f * 0.5F;
        this.thumbHalfHeight = this.thumbImage.getHeight() * 0.5F;
        this.normalizedMinValue = 0.0D;
        this.normalizedMaxValue = 1.0D;
        this.pressedThumb = null;
        this.notifyWhileDragging = false;
        this.mActivePointerId = 255;
        init(paramContext, paramAttributeSet);
    }

    private void attemptClaimDrag() {
        if (getParent() != null)
            getParent().requestDisallowInterceptTouchEvent(true);
    }

    private void drawThumb(float paramFloat, boolean paramBoolean1, Canvas paramCanvas, boolean paramBoolean2) {
        Bitmap bitmap;
        if (paramBoolean2) {
            bitmap = this.thumbDisabledImage;
        } else if (paramBoolean1) {
            bitmap = this.thumbPressedImage;
        } else {
            bitmap = this.thumbImage;
        }
        paramCanvas.drawBitmap(bitmap, paramFloat - this.thumbHalfWidth, this.mTextOffset, this.paint);
    }

    private Thumb evalPressedThumb(float paramFloat) {
        Thumb thumb = null;
        boolean bool1 = isInThumbRange(paramFloat, this.normalizedMinValue);
        boolean bool2 = isInThumbRange(paramFloat, this.normalizedMaxValue);
        if (bool1 && bool2) {
            if (paramFloat / getWidth() > 0.5F) {
                thumb = Thumb.MIN;
            } else {
                thumb = Thumb.MAX;
            }
        } else if (bool1) {
            thumb = Thumb.MIN;
        } else if (bool2) {
            thumb = Thumb.MAX;
        }
        return thumb;
    }

    private T extractNumericValueFromAttributes(TypedArray paramTypedArray, int paramInt1, int paramInt2) {
        TypedValue typedValue = paramTypedArray.peekValue(paramInt1);
        return (T)((typedValue == null) ? Integer.valueOf(paramInt2) : ((typedValue.type == 4) ? Float.valueOf(paramTypedArray.getFloat(paramInt1, paramInt2)) : Integer.valueOf(paramTypedArray.getInteger(paramInt1, paramInt2))));
    }

    private void init(Context paramContext, AttributeSet paramAttributeSet) {
        if (paramAttributeSet == null) {
            setRangeToDefaultValues();
        } else {
            TypedArray typedArray = getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.RangeSeekBar, 0, 0);
            setRangeValues(extractNumericValueFromAttributes(typedArray, 1, DEFAULT_MINIMUM.intValue()), extractNumericValueFromAttributes(typedArray, 0, DEFAULT_MAXIMUM.intValue()));
            this.mSingleThumb = typedArray.getBoolean(2, false);
            typedArray.recycle();
        }
        setValuePrimAndNumberType();
        this.INITIAL_PADDING = PixelUtil.dpToPx(paramContext, 8);
        this.mTextSize = PixelUtil.dpToPx(paramContext, 14);
        this.mDistanceToTop = PixelUtil.dpToPx(paramContext, 8);
        this.mTextOffset = this.mTextSize + PixelUtil.dpToPx(paramContext, 8) + this.mDistanceToTop;
        float f = PixelUtil.dpToPx(paramContext, 1);
        this.mRect = new RectF(this.padding, this.mTextOffset + this.thumbHalfHeight - f / 2.0F, getWidth() - this.padding, this.mTextOffset + this.thumbHalfHeight + f / 2.0F);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.mScaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    private boolean isInThumbRange(float paramFloat, double paramDouble) {
        boolean bool;
        if (Math.abs(paramFloat - normalizedToScreen(paramDouble)) <= this.thumbHalfWidth) {
            bool = true;
        } else {
            bool = false;
        }
        return bool;
    }

    private float normalizedToScreen(double paramDouble) {
        double d1 = this.padding;
        double d2 = (getWidth() - this.padding * 2.0F);
        Double.isNaN(d2);
        Double.isNaN(d1);
        return (float)(d1 + d2 * paramDouble);
    }

    private T normalizedToValue(double paramDouble) {
        double d1 = this.absoluteMinValuePrim;
        double d2 = this.absoluteMaxValuePrim;
        NumberType numberType = this.numberType;
        paramDouble = Math.round((d1 + (d2 - d1) * paramDouble) * 100.0D);
        Double.isNaN(paramDouble);
        return (T)numberType.toNumber(paramDouble / 100.0D);
    }

    private final void onSecondaryPointerUp(MotionEvent paramMotionEvent) {
        int i = (paramMotionEvent.getAction() & 0xFF00) >> 8;
        if (paramMotionEvent.getPointerId(i) == this.mActivePointerId) {
            if (i == 0) {
                i = 1;
            } else {
                i = 0;
            }
            this.mDownMotionX = paramMotionEvent.getX(i);
            this.mActivePointerId = paramMotionEvent.getPointerId(i);
        }
    }

    private double screenToNormalized(float paramFloat) {
        int i = getWidth();
        float f1 = i;
        float f2 = this.padding;
        return (f1 <= f2 * 2.0F) ? 0.0D : Math.min(1.0D, Math.max(0.0D, ((paramFloat - f2) / (i - f2 * 2.0F))));
    }

    private void setNormalizedMaxValue(double paramDouble) {
        this.normalizedMaxValue = Math.max(0.0D, Math.min(1.0D, Math.max(paramDouble, this.normalizedMinValue)));
        invalidate();
    }

    private void setNormalizedMinValue(double paramDouble) {
        this.normalizedMinValue = Math.max(0.0D, Math.min(1.0D, Math.min(paramDouble, this.normalizedMaxValue)));
        invalidate();
    }

    private void setRangeToDefaultValues() {
        this.absoluteMinValue = (T)DEFAULT_MINIMUM;
        this.absoluteMaxValue = (T)DEFAULT_MAXIMUM;
        setValuePrimAndNumberType();
    }

    private void setValuePrimAndNumberType() {
        this.absoluteMinValuePrim = this.absoluteMinValue.doubleValue();
        this.absoluteMaxValuePrim = this.absoluteMaxValue.doubleValue();
        this.numberType = NumberType.fromNumber(this.absoluteMinValue);
    }

    private final void trackTouchEvent(MotionEvent paramMotionEvent) {
        float f = paramMotionEvent.getX(paramMotionEvent.findPointerIndex(this.mActivePointerId));
        if (Thumb.MIN.equals(this.pressedThumb) && !this.mSingleThumb) {
            setNormalizedMinValue(screenToNormalized(f));
        } else if (Thumb.MAX.equals(this.pressedThumb)) {
            setNormalizedMaxValue(screenToNormalized(f));
        }
    }

    private double valueToNormalized(T paramT) {
        if (0.0D == this.absoluteMaxValuePrim - this.absoluteMinValuePrim)
            return 0.0D;
        double d1 = paramT.doubleValue();
        double d2 = this.absoluteMinValuePrim;
        return (d1 - d2) / (this.absoluteMaxValuePrim - d2);
    }

    public T getAbsoluteMaxValue() {
        return this.absoluteMaxValue;
    }

    public T getAbsoluteMinValue() {
        return this.absoluteMinValue;
    }

    public T getSelectedMaxValue() {
        return normalizedToValue(this.normalizedMaxValue);
    }

    public T getSelectedMinValue() {
        return normalizedToValue(this.normalizedMinValue);
    }

    public boolean isNotifyWhileDragging() {
        return this.notifyWhileDragging;
    }

    protected void onDraw(Canvas paramCanvas) {
        // Byte code:
        //   0: aload_0
        //   1: monitorenter
        //   2: aload_0
        //   3: aload_1
        //   4: invokespecial onDraw : (Landroid/graphics/Canvas;)V
        //   7: aload_0
        //   8: getfield paint : Landroid/graphics/Paint;
        //   11: aload_0
        //   12: getfield mTextSize : I
        //   15: i2f
        //   16: invokevirtual setTextSize : (F)V
        //   19: aload_0
        //   20: getfield paint : Landroid/graphics/Paint;
        //   23: getstatic android/graphics/Paint$Style.FILL : Landroid/graphics/Paint$Style;
        //   26: invokevirtual setStyle : (Landroid/graphics/Paint$Style;)V
        //   29: aload_0
        //   30: getfield paint : Landroid/graphics/Paint;
        //   33: astore_2
        //   34: ldc_w -7829368
        //   37: istore_3
        //   38: aload_2
        //   39: ldc_w -7829368
        //   42: invokevirtual setColor : (I)V
        //   45: aload_0
        //   46: getfield paint : Landroid/graphics/Paint;
        //   49: astore_2
        //   50: iconst_1
        //   51: istore #4
        //   53: aload_2
        //   54: iconst_1
        //   55: invokevirtual setAntiAlias : (Z)V
        //   58: aload_0
        //   59: invokevirtual getContext : ()Landroid/content/Context;
        //   62: ldc_w 2131623986
        //   65: invokevirtual getString : (I)Ljava/lang/String;
        //   68: astore #5
        //   70: aload_0
        //   71: invokevirtual getContext : ()Landroid/content/Context;
        //   74: ldc_w 2131623985
        //   77: invokevirtual getString : (I)Ljava/lang/String;
        //   80: astore_2
        //   81: aload_0
        //   82: getfield paint : Landroid/graphics/Paint;
        //   85: aload #5
        //   87: invokevirtual measureText : (Ljava/lang/String;)F
        //   90: aload_0
        //   91: getfield paint : Landroid/graphics/Paint;
        //   94: aload_2
        //   95: invokevirtual measureText : (Ljava/lang/String;)F
        //   98: invokestatic max : (FF)F
        //   101: fstore #6
        //   103: aload_0
        //   104: getfield mTextOffset : I
        //   107: i2f
        //   108: aload_0
        //   109: getfield thumbHalfHeight : F
        //   112: fadd
        //   113: aload_0
        //   114: getfield mTextSize : I
        //   117: iconst_3
        //   118: idiv
        //   119: i2f
        //   120: fadd
        //   121: fstore #7
        //   123: aload_1
        //   124: aload #5
        //   126: fconst_0
        //   127: fload #7
        //   129: aload_0
        //   130: getfield paint : Landroid/graphics/Paint;
        //   133: invokevirtual drawText : (Ljava/lang/String;FFLandroid/graphics/Paint;)V
        //   136: aload_1
        //   137: aload_2
        //   138: aload_0
        //   139: invokevirtual getWidth : ()I
        //   142: i2f
        //   143: fload #6
        //   145: fsub
        //   146: fload #7
        //   148: aload_0
        //   149: getfield paint : Landroid/graphics/Paint;
        //   152: invokevirtual drawText : (Ljava/lang/String;FFLandroid/graphics/Paint;)V
        //   155: aload_0
        //   156: getfield INITIAL_PADDING : F
        //   159: fload #6
        //   161: fadd
        //   162: aload_0
        //   163: getfield thumbHalfWidth : F
        //   166: fadd
        //   167: fstore #6
        //   169: aload_0
        //   170: fload #6
        //   172: putfield padding : F
        //   175: aload_0
        //   176: getfield mRect : Landroid/graphics/RectF;
        //   179: fload #6
        //   181: putfield left : F
        //   184: aload_0
        //   185: getfield mRect : Landroid/graphics/RectF;
        //   188: aload_0
        //   189: invokevirtual getWidth : ()I
        //   192: i2f
        //   193: aload_0
        //   194: getfield padding : F
        //   197: fsub
        //   198: putfield right : F
        //   201: aload_1
        //   202: aload_0
        //   203: getfield mRect : Landroid/graphics/RectF;
        //   206: aload_0
        //   207: getfield paint : Landroid/graphics/Paint;
        //   210: invokevirtual drawRect : (Landroid/graphics/RectF;Landroid/graphics/Paint;)V
        //   213: aload_0
        //   214: invokevirtual getSelectedMinValue : ()Ljava/lang/Number;
        //   217: aload_0
        //   218: invokevirtual getAbsoluteMinValue : ()Ljava/lang/Number;
        //   221: invokevirtual equals : (Ljava/lang/Object;)Z
        //   224: ifeq -> 244
        //   227: aload_0
        //   228: invokevirtual getSelectedMaxValue : ()Ljava/lang/Number;
        //   231: aload_0
        //   232: invokevirtual getAbsoluteMaxValue : ()Ljava/lang/Number;
        //   235: invokevirtual equals : (Ljava/lang/Object;)Z
        //   238: ifeq -> 244
        //   241: goto -> 247
        //   244: iconst_0
        //   245: istore #4
        //   247: iload #4
        //   249: ifeq -> 255
        //   252: goto -> 259
        //   255: getstatic tamhoang/ldpro4/RangeSeekBar.DEFAULT_COLOR : I
        //   258: istore_3
        //   259: aload_0
        //   260: getfield mRect : Landroid/graphics/RectF;
        //   263: aload_0
        //   264: aload_0
        //   265: getfield normalizedMinValue : D
        //   268: invokespecial normalizedToScreen : (D)F
        //   271: putfield left : F
        //   274: aload_0
        //   275: getfield mRect : Landroid/graphics/RectF;
        //   278: aload_0
        //   279: aload_0
        //   280: getfield normalizedMaxValue : D
        //   283: invokespecial normalizedToScreen : (D)F
        //   286: putfield right : F
        //   289: aload_0
        //   290: getfield paint : Landroid/graphics/Paint;
        //   293: iload_3
        //   294: invokevirtual setColor : (I)V
        //   297: aload_1
        //   298: aload_0
        //   299: getfield mRect : Landroid/graphics/RectF;
        //   302: aload_0
        //   303: getfield paint : Landroid/graphics/Paint;
        //   306: invokevirtual drawRect : (Landroid/graphics/RectF;Landroid/graphics/Paint;)V
        //   309: aload_0
        //   310: getfield mSingleThumb : Z
        //   313: ifne -> 341
        //   316: aload_0
        //   317: aload_0
        //   318: aload_0
        //   319: getfield normalizedMinValue : D
        //   322: invokespecial normalizedToScreen : (D)F
        //   325: getstatic tamhoang/ldpro4/RangeSeekBar$Thumb.MIN : Ltamhoang/ldpro4/RangeSeekBar$Thumb;
        //   328: aload_0
        //   329: getfield pressedThumb : Ltamhoang/ldpro4/RangeSeekBar$Thumb;
        //   332: invokevirtual equals : (Ljava/lang/Object;)Z
        //   335: aload_1
        //   336: iload #4
        //   338: invokespecial drawThumb : (FZLandroid/graphics/Canvas;Z)V
        //   341: aload_0
        //   342: aload_0
        //   343: aload_0
        //   344: getfield normalizedMaxValue : D
        //   347: invokespecial normalizedToScreen : (D)F
        //   350: getstatic tamhoang/ldpro4/RangeSeekBar$Thumb.MAX : Ltamhoang/ldpro4/RangeSeekBar$Thumb;
        //   353: aload_0
        //   354: getfield pressedThumb : Ltamhoang/ldpro4/RangeSeekBar$Thumb;
        //   357: invokevirtual equals : (Ljava/lang/Object;)Z
        //   360: aload_1
        //   361: iload #4
        //   363: invokespecial drawThumb : (FZLandroid/graphics/Canvas;Z)V
        //   366: iload #4
        //   368: ifne -> 528
        //   371: aload_0
        //   372: getfield paint : Landroid/graphics/Paint;
        //   375: aload_0
        //   376: getfield mTextSize : I
        //   379: i2f
        //   380: invokevirtual setTextSize : (F)V
        //   383: aload_0
        //   384: getfield paint : Landroid/graphics/Paint;
        //   387: ldc_w -16777216
        //   390: invokevirtual setColor : (I)V
        //   393: aload_0
        //   394: invokevirtual getContext : ()Landroid/content/Context;
        //   397: iconst_3
        //   398: invokestatic dpToPx : (Landroid/content/Context;I)I
        //   401: istore_3
        //   402: aload_0
        //   403: invokevirtual getSelectedMinValue : ()Ljava/lang/Number;
        //   406: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
        //   409: astore_2
        //   410: aload_0
        //   411: invokevirtual getSelectedMaxValue : ()Ljava/lang/Number;
        //   414: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
        //   417: astore #5
        //   419: aload_0
        //   420: getfield paint : Landroid/graphics/Paint;
        //   423: aload_2
        //   424: invokevirtual measureText : (Ljava/lang/String;)F
        //   427: fstore #8
        //   429: iload_3
        //   430: i2f
        //   431: fstore #6
        //   433: aload_0
        //   434: getfield paint : Landroid/graphics/Paint;
        //   437: aload #5
        //   439: invokevirtual measureText : (Ljava/lang/String;)F
        //   442: fstore #9
        //   444: iload_3
        //   445: i2f
        //   446: fstore #7
        //   448: aload_0
        //   449: getfield mSingleThumb : Z
        //   452: ifne -> 491
        //   455: aload_1
        //   456: aload_2
        //   457: aload_0
        //   458: aload_0
        //   459: getfield normalizedMinValue : D
        //   462: invokespecial normalizedToScreen : (D)F
        //   465: fload #8
        //   467: fload #6
        //   469: fadd
        //   470: ldc 0.5
        //   472: fmul
        //   473: fsub
        //   474: aload_0
        //   475: getfield mDistanceToTop : I
        //   478: aload_0
        //   479: getfield mTextSize : I
        //   482: iadd
        //   483: i2f
        //   484: aload_0
        //   485: getfield paint : Landroid/graphics/Paint;
        //   488: invokevirtual drawText : (Ljava/lang/String;FFLandroid/graphics/Paint;)V
        //   491: aload_1
        //   492: aload #5
        //   494: aload_0
        //   495: aload_0
        //   496: getfield normalizedMaxValue : D
        //   499: invokespecial normalizedToScreen : (D)F
        //   502: ldc 0.5
        //   504: fload #9
        //   506: fload #7
        //   508: fadd
        //   509: fmul
        //   510: fsub
        //   511: aload_0
        //   512: getfield mDistanceToTop : I
        //   515: aload_0
        //   516: getfield mTextSize : I
        //   519: iadd
        //   520: i2f
        //   521: aload_0
        //   522: getfield paint : Landroid/graphics/Paint;
        //   525: invokevirtual drawText : (Ljava/lang/String;FFLandroid/graphics/Paint;)V
        //   528: aload_0
        //   529: monitorexit
        //   530: return
        //   531: astore_1
        //   532: aload_0
        //   533: monitorexit
        //   534: aload_1
        //   535: athrow
        // Exception table:
        //   from	to	target	type
        //   2	34	531	finally
        //   38	50	531	finally
        //   53	241	531	finally
        //   255	259	531	finally
        //   259	341	531	finally
        //   341	366	531	finally
        //   371	429	531	finally
        //   433	444	531	finally
        //   448	491	531	finally
        //   491	528	531	finally
    }

    protected void onMeasure(int paramInt1, int paramInt2) {
        // Byte code:
        //   0: aload_0
        //   1: monitorenter
        //   2: sipush #200
        //   5: istore_3
        //   6: iload_1
        //   7: invokestatic getMode : (I)I
        //   10: ifeq -> 18
        //   13: iload_1
        //   14: invokestatic getSize : (I)I
        //   17: istore_3
        //   18: aload_0
        //   19: getfield thumbImage : Landroid/graphics/Bitmap;
        //   22: invokevirtual getHeight : ()I
        //   25: aload_0
        //   26: invokevirtual getContext : ()Landroid/content/Context;
        //   29: bipush #30
        //   31: invokestatic dpToPx : (Landroid/content/Context;I)I
        //   34: iadd
        //   35: istore #4
        //   37: iload #4
        //   39: istore_1
        //   40: iload_2
        //   41: invokestatic getMode : (I)I
        //   44: ifeq -> 57
        //   47: iload #4
        //   49: iload_2
        //   50: invokestatic getSize : (I)I
        //   53: invokestatic min : (II)I
        //   56: istore_1
        //   57: aload_0
        //   58: iload_3
        //   59: iload_1
        //   60: invokevirtual setMeasuredDimension : (II)V
        //   63: aload_0
        //   64: monitorexit
        //   65: return
        //   66: astore #5
        //   68: aload_0
        //   69: monitorexit
        //   70: aload #5
        //   72: athrow
        // Exception table:
        //   from	to	target	type
        //   6	18	66	finally
        //   18	37	66	finally
        //   40	57	66	finally
        //   57	63	66	finally
    }

    protected void onRestoreInstanceState(Parcelable paramParcelable) {
        Bundle bundle = (Bundle)paramParcelable;
        super.onRestoreInstanceState(bundle.getParcelable("SUPER"));
        this.normalizedMinValue = bundle.getDouble("MIN");
        this.normalizedMaxValue = bundle.getDouble("MAX");
    }

    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("SUPER", super.onSaveInstanceState());
        bundle.putDouble("MIN", this.normalizedMinValue);
        bundle.putDouble("MAX", this.normalizedMaxValue);
        return (Parcelable)bundle;
    }

    void onStartTrackingTouch() {
        this.mIsDragging = true;
    }

    void onStopTrackingTouch() {
        this.mIsDragging = false;
    }

    public boolean onTouchEvent(MotionEvent paramMotionEvent) {
        OnRangeSeekBarChangeListener<T> onRangeSeekBarChangeListener;
        if (!isEnabled())
            return false;
        int i = paramMotionEvent.getAction() & 0xFF;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 5) {
                            if (i == 6) {
                                onSecondaryPointerUp(paramMotionEvent);
                                invalidate();
                            }
                        } else {
                            i = paramMotionEvent.getPointerCount() - 1;
                            this.mDownMotionX = paramMotionEvent.getX(i);
                            this.mActivePointerId = paramMotionEvent.getPointerId(i);
                            invalidate();
                        }
                    } else {
                        if (this.mIsDragging) {
                            onStopTrackingTouch();
                            setPressed(false);
                        }
                        invalidate();
                    }
                } else if (this.pressedThumb != null) {
                    if (this.mIsDragging) {
                        trackTouchEvent(paramMotionEvent);
                    } else if (Math.abs(paramMotionEvent.getX(paramMotionEvent.findPointerIndex(this.mActivePointerId)) - this.mDownMotionX) > this.mScaledTouchSlop) {
                        setPressed(true);
                        invalidate();
                        onStartTrackingTouch();
                        trackTouchEvent(paramMotionEvent);
                        attemptClaimDrag();
                    }
                    if (this.notifyWhileDragging) {
                        onRangeSeekBarChangeListener = this.listener;
                        if (onRangeSeekBarChangeListener != null)
                            onRangeSeekBarChangeListener.onRangeSeekBarValuesChanged(this, getSelectedMinValue(), getSelectedMaxValue());
                    }
                }
            } else {
                if (this.mIsDragging) {
                    trackTouchEvent((MotionEvent)onRangeSeekBarChangeListener);
                    onStopTrackingTouch();
                    setPressed(false);
                } else {
                    onStartTrackingTouch();
                    trackTouchEvent((MotionEvent)onRangeSeekBarChangeListener);
                    onStopTrackingTouch();
                }
                this.pressedThumb = null;
                invalidate();
                onRangeSeekBarChangeListener = this.listener;
                if (onRangeSeekBarChangeListener != null)
                    onRangeSeekBarChangeListener.onRangeSeekBarValuesChanged(this, getSelectedMinValue(), getSelectedMaxValue());
            }
        } else {
            i = onRangeSeekBarChangeListener.getPointerId(onRangeSeekBarChangeListener.getPointerCount() - 1);
            this.mActivePointerId = i;
            float f = onRangeSeekBarChangeListener.getX(onRangeSeekBarChangeListener.findPointerIndex(i));
            this.mDownMotionX = f;
            Thumb thumb = evalPressedThumb(f);
            this.pressedThumb = thumb;
            if (thumb == null)
                return super.onTouchEvent((MotionEvent)onRangeSeekBarChangeListener);
            setPressed(true);
            invalidate();
            onStartTrackingTouch();
            trackTouchEvent((MotionEvent)onRangeSeekBarChangeListener);
            attemptClaimDrag();
        }
        return true;
    }

    public void resetSelectedValues() {
        setSelectedMinValue(this.absoluteMinValue);
        setSelectedMaxValue(this.absoluteMaxValue);
    }

    public void setNotifyWhileDragging(boolean paramBoolean) {
        this.notifyWhileDragging = paramBoolean;
    }

    public void setOnRangeSeekBarChangeListener(OnRangeSeekBarChangeListener<T> paramOnRangeSeekBarChangeListener) {
        this.listener = paramOnRangeSeekBarChangeListener;
    }

    public void setRangeValues(T paramT1, T paramT2) {
        this.absoluteMinValue = paramT1;
        this.absoluteMaxValue = paramT2;
        setValuePrimAndNumberType();
    }

    public void setSelectedMaxValue(T paramT) {
        if (0.0D == this.absoluteMaxValuePrim - this.absoluteMinValuePrim) {
            setNormalizedMaxValue(1.0D);
        } else {
            setNormalizedMaxValue(valueToNormalized(paramT));
        }
    }

    public void setSelectedMinValue(T paramT) {
        if (0.0D == this.absoluteMaxValuePrim - this.absoluteMinValuePrim) {
            setNormalizedMinValue(0.0D);
        } else {
            setNormalizedMinValue(valueToNormalized(paramT));
        }
    }

    private enum NumberType {
        BIG_DECIMAL, BYTE, DOUBLE, FLOAT, INTEGER, LONG, SHORT;

        static {
            FLOAT = new NumberType("FLOAT", 3);
            SHORT = new NumberType("SHORT", 4);
            BYTE = new NumberType("BYTE", 5);
            NumberType numberType = new NumberType("BIG_DECIMAL", 6);
            BIG_DECIMAL = numberType;
            $VALUES = new NumberType[] { LONG, DOUBLE, INTEGER, FLOAT, SHORT, BYTE, numberType };
        }

        public static <E extends Number> NumberType fromNumber(E param1E) throws IllegalArgumentException {
            if (param1E instanceof Long)
                return LONG;
            if (param1E instanceof Double)
                return DOUBLE;
            if (param1E instanceof Integer)
                return INTEGER;
            if (param1E instanceof Float)
                return FLOAT;
            if (param1E instanceof Short)
                return SHORT;
            if (param1E instanceof Byte)
                return BYTE;
            if (param1E instanceof BigDecimal)
                return BIG_DECIMAL;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Number class '");
            stringBuilder.append(param1E.getClass().getName());
            stringBuilder.append("' is not supported");
            throw new IllegalArgumentException(stringBuilder.toString());
        }

        public Number toNumber(double param1Double) {
            StringBuilder stringBuilder;
            switch (this) {
                default:
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("can't convert ");
                    stringBuilder.append(this);
                    stringBuilder.append(" to a Number object");
                    throw new InstantiationError(stringBuilder.toString());
                case null:
                    return BigDecimal.valueOf(param1Double);
                case null:
                    return Byte.valueOf((byte)(int)param1Double);
                case null:
                    return Short.valueOf((short)(int)param1Double);
                case null:
                    return Float.valueOf((float)param1Double);
                case null:
                    return Integer.valueOf((int)param1Double);
                case null:
                    return Double.valueOf(param1Double);
                case null:
                    break;
            }
            return Long.valueOf((long)param1Double);
        }
    }

    public static interface OnRangeSeekBarChangeListener<T> {
        void onRangeSeekBarValuesChanged(RangeSeekBar<?> param1RangeSeekBar, T param1T1, T param1T2);
    }

    private enum Thumb {
        MAX, MIN;

        static {
            Thumb thumb = new Thumb("MAX", 1);
            MAX = thumb;
            $VALUES = new Thumb[] { MIN, thumb };
        }
    }
}
