package io.github.pps5.kakaosampleapp.feature.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import io.github.pps5.kakaosampleapp.databinding.FragmentEventDetailBinding

class EventDetailFragment : Fragment() {

    private lateinit var binding: FragmentEventDetailBinding
    private val args: EventDetailFragmentArgs by navArgs()

    private val onClickListener = View.OnClickListener {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(args.url)))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentEventDetailBinding.inflate(inflater, container, false).also {
            it.listener = onClickListener
        }
        setUpWebView()
        return binding.root
    }

    private fun setUpWebView() {
        binding.detailWebview.settings.apply {
            builtInZoomControls = false
            setSupportZoom(false)
            setAppCacheEnabled(true)
        }
        binding.detailWebview.overScrollMode = View.OVER_SCROLL_NEVER
        WebView.setWebContentsDebuggingEnabled(true)
        val html = String.format(
            TEMPLATE,
            args.title,
            args.description,
            56 /* px */
        )
        binding.detailWebview.loadData(html, "text/html", "UTF-8")
    }
}