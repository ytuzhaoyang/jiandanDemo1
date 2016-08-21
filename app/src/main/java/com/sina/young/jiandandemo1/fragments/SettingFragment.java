package com.sina.young.jiandandemo1.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import com.afollestad.materialdialogs.MaterialDialog;
import com.sina.young.jiandandemo1.R;

import utils.AppInfoUtil;
import utils.ShowToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {

    public static final String CLEAR_CACHE = "clear_cache";
    public static final String ABOUT_APP = "about_app";
    public static final String APP_VERSION = "app_version";
    public static final String ENABLE_SISTER = "enable_sister";
    public static final String ENABLE_FRESH_BIG = "enable_fresh_big";

    private Preference clearCache;
    private Preference aboutApp;
    private Preference appVersion;
    private CheckBoxPreference enableSister;
    private CheckBoxPreference enableBig;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.per_setting);

        clearCache = findPreference(CLEAR_CACHE);
        aboutApp = findPreference(ABOUT_APP);
        appVersion = findPreference(APP_VERSION);
        enableBig = (CheckBoxPreference) findPreference(ENABLE_FRESH_BIG);
        enableSister = (CheckBoxPreference) findPreference(ENABLE_SISTER);

        appVersion.setTitle(AppInfoUtil.getVersionName(getActivity()));

//        File cacheFile = com.nostra13.universalimageloader.core.ImageLoader.getInstance().getDiskCache().getDirectory();
//        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        clearCache.setSummary("缓存大小:");

        enableSister.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                ShowToast.Short(((boolean) newValue) ? "已解锁隐藏属性->妹子图" : "已关闭隐藏属性->妹子图");
                return true;
            }
        });

        enableBig.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                ShowToast.Short(((Boolean) newValue) ? "已开启大图模式" : "已关闭大图模式");
                return true;
            }
        });
        clearCache.setOnPreferenceClickListener(this);
        aboutApp.setOnPreferenceClickListener(this);

    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        String key = preference.getKey();

        if (key.equals(CLEAR_CACHE)) {
            //ImageLoader.getInstance().clearDiskCache();
            ShowToast.Short("清除缓存成功");
            clearCache.setSummary("缓存大小：0.00M");
        } else if (key.equals(ABOUT_APP)) {
            MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
                    .title("煎蛋练习版")
                    .backgroundColor(ContextCompat.getColor(getActivity(), R.color.primary))
                    .content("热爱分享，欢迎star ^_^")
                    .positiveColor(ContextCompat.getColor(getActivity(), R.color.actionbar_title))
                    .negativeColor(ContextCompat.getColor(getActivity(), R.color.actionbar_title))
                    .neutralColor(ContextCompat.getColor(getActivity(), R.color.actionbar_title))
                    .positiveText("CSDN")
                    .negativeText("GITHUB")
                    .neutralText("WEIBO")
                    .callback(new MaterialDialog.ButtonCallback() {
                        @Override
                        public void onPositive(MaterialDialog dialog) {
                            super.onPositive(dialog);
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ZhaoKaiQiang/JianDan")));
                            dialog.dismiss();
                        }

                        @Override
                        public void onNegative(MaterialDialog dialog) {
                            super.onNegative(dialog);
                            super.onNegative(dialog);
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://weibo.com/zhaokaiqiang1992")));
                        }

                        @Override
                        public void onNeutral(MaterialDialog dialog) {
                            super.onNeutral(dialog);
                            super.onNeutral(dialog);
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net/zhaokaiqiang1992")));
                        }
                    })
                    .show();
        }
        return true;
    }
}
