package tamhoang.ldpro4;

import android.content.Context;

public class PixelUtil {
    public static int dpToPx(Context paramContext, int paramInt) {
        return Math.round(paramInt * getPixelScaleFactor(paramContext));
    }

    private static float getPixelScaleFactor(Context paramContext) {
        return (paramContext.getResources().getDisplayMetrics()).xdpi / 160.0F;
    }

    public static int pxToDp(Context paramContext, int paramInt) {
        return Math.round(paramInt / getPixelScaleFactor(paramContext));
    }
}
