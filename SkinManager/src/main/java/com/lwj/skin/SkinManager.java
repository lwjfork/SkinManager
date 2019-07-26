package com.lwj.skin;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.text.TextUtils;

import com.lwj.skin.fetcher.ImageSrcDrawableFetcher;
import com.lwj.skin.fetcher.TextColorFetcher;
import com.lwj.skin.fetcher.base.ResFetcher;
import com.lwj.skin.util.Reflection;
import com.lwj.skin.util.SkinInflaterFactory;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by lwj on 2019/7/26.
 * lwjfork@gmail.com
 */

public class SkinManager {

    private SharedPreferences preferences;
    private static final String SP_SKIN_PATH_KEY = "SKIN";
    private Application originContext;
    private String skinPackageName;//  // 插件包名--唯一的，依插件包名为唯一标志

    private Resources skinRes;// 插件 res
    private Resources appRes; // app res


    private HashMap<String, ResFetcher> supportAttrs = new HashMap<>();

    private SkinManager() {

    }


    private static class InnerHolder {
        private static SkinManager INSTANCE = new SkinManager();

    }

    public static SkinManager getInstance() {
        return InnerHolder.INSTANCE;
    }

    public void init(Application application) {
        Reflection.unseal(application);
        addFetcher();
        this.originContext = application;
        this.appRes = originContext.getResources();
        preferences = originContext.getSharedPreferences(SP_SKIN_PATH_KEY, Context.MODE_PRIVATE);
        String path = preferences.getString(SP_SKIN_PATH_KEY, "");
        if (!TextUtils.isEmpty(path)) { //  初始化的时候，如果为空，那么什么也不做
            loadSkin(path);
        }
    }

    private void addFetcher() {
        addFetcher(new TextColorFetcher())

                .addFetcher(new ImageSrcDrawableFetcher());
    }

    // 添加资源抓取器
    public SkinManager addFetcher(ResFetcher resFetcher) {
        if (resFetcher != null) {
            String attrName = resFetcher.getAttrName();
            supportAttrs.put(attrName, resFetcher);
        }
        return this;
    }

    public ResFetcher getFetcher(String attrName) {
        return supportAttrs.get(attrName);
    }

    // 添加资源抓取器
    public SkinManager removeFetcher(ResFetcher resFetcher) {
        if (resFetcher != null) {
            String attrName = resFetcher.getAttrName();
            supportAttrs.remove(attrName);
        }
        return this;
    }


    // 只有当有皮肤资源的时候，我们才设置皮肤
    public void apply(SkinInflaterFactory factory) {
        if (factory != null && skinRes != null) {
            factory.apply();
        }

    }

    public Resources getSkinRes() {
        return skinRes;
    }

    public Resources getAppRes() {
        return appRes;
    }

    public String getSkinPackageName() {
        return skinPackageName;
    }

    public boolean containAttr(String attrName) {
        return supportAttrs.containsKey(attrName);
    }

    public boolean haveSupportAttr() {
        return supportAttrs != null && supportAttrs.size() > 0;
    }


    public void loadSkin(String path) {
        String currentPath = preferences.getString(SP_SKIN_PATH_KEY, "");
        if (currentPath.equals(path)) { // 同一个皮肤，不变
            return;
        }
        if (TextUtils.isEmpty(path)) {

            //如果是空，就加载默认资源,同时要清空皮肤包的配置
            reset();

        } else {
            try {
                AssetManager assetManager = AssetManager.class.newInstance();
                Method method = assetManager.getClass().getMethod("addAssetPath", String.class);
                method.setAccessible(true);
                method.invoke(assetManager, path);

                //皮肤包对应的 Resources
                skinRes = new Resources(assetManager, appRes.getDisplayMetrics(), appRes.getConfiguration());

                //获取包名
                PackageManager packageManager = originContext.getPackageManager();
                PackageInfo info = packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
                if (info != null) {
                    skinPackageName = info.packageName;
                    preferences.edit().putString(SP_SKIN_PATH_KEY, path).apply();
                } else {
                    reset();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public int getSkinResId(int resId) {
        String entryName = appRes.getResourceEntryName(resId);
        String typeName = appRes.getResourceTypeName(resId);
        return getSkinResId(resId, entryName, typeName);
    }

    public int getSkinResId(int resId, String entryName, String typeName) {
        if (skinRes == null) {
            return resId;
        } else {
            return skinRes.getIdentifier(entryName, typeName, skinPackageName);
        }
    }


    private void reset() {
        //如果是空，就加载默认资源,同时要清空皮肤包的配置
        preferences.edit().putString(SP_SKIN_PATH_KEY, "").apply();
        skinRes = appRes;
        skinPackageName = originContext.getPackageName();
    }

}


