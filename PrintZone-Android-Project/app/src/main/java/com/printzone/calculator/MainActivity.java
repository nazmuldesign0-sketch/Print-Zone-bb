package com.printzone.calculator;
import android.app.Activity;import android.content.*;import android.os.Bundle;import android.webkit.*;
public class MainActivity extends Activity{
 WebView w;
 @Override protected void onCreate(Bundle b){super.onCreate(b);
  w=new WebView(this);setContentView(w);
  WebSettings s=w.getSettings();s.setJavaScriptEnabled(true);s.setDomStorageEnabled(true);s.setAllowFileAccess(true);
  w.setWebViewClient(new WebViewClient());w.setWebChromeClient(new WebChromeClient());
  w.addJavascriptInterface(new Object(){
   @JavascriptInterface public void share(String t){Intent i=new Intent(Intent.ACTION_SEND);i.setType("text/plain");i.putExtra(Intent.EXTRA_TEXT,t);startActivity(Intent.createChooser(i,"Save backup"));}
   @JavascriptInterface public String getClipboard(){ClipboardManager c=(ClipboardManager)getSystemService(CLIPBOARD_SERVICE);return c.hasPrimaryClip()&&c.getPrimaryClip().getItemCount()>0?String.valueOf(c.getPrimaryClip().getItemAt(0).getText()):"";}
  },"Android");
  w.loadUrl("file:///android_asset/index.html");}
 @Override public void onBackPressed(){if(w.canGoBack())w.goBack();else super.onBackPressed();}
}
