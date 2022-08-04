package com.petsprogramingtest.uifragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.petsprogramingtest.R
import com.petsprogramingtest.utils.Constants
import com.petsprogramingtest.utils.hideToolbar
import com.petsprogramingtest.utils.showsToolbar
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val contentUrl = arguments?.getString(Constants.KEY_CONTENT)
        if (contentUrl != null) {
            webview.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView, weburl: String) {

                }
            }

            webview.webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView, newProgress: Int) {
                    tvLoading.text = "Page loading : $newProgress%"
                    if (newProgress == 100) {
                        tvLoading.visibility = View.GONE
                    }
                }
            }
            webview.loadUrl(contentUrl)
        }
    }

    override fun onResume() {
        super.onResume()
        hideToolbar(requireActivity())
    }

    override fun onStop() {
        super.onStop()
        showsToolbar(requireActivity())
    }

}