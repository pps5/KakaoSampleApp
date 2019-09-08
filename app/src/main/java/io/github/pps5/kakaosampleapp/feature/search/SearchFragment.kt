package io.github.pps5.kakaosampleapp.feature.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import io.github.pps5.kakaosampleapp.common.vo.Resource
import io.github.pps5.kakaosampleapp.data.entity.Event
import io.github.pps5.kakaosampleapp.databinding.FragmentSearchBinding
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SearchFragment : Fragment(), SearchResultAdapter.OnClickEventListener {

    private lateinit var binding: FragmentSearchBinding
    private val args: SearchFragmentArgs by navArgs()
    private val viewModel: SearchViewModel by inject { parametersOf(args.query) }
    private val adapter = SearchResultAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false).also {
            it.searchResultRecycler.adapter = adapter
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }
        viewModel.events.observe(this, Observer {
            when (it.status) {
                Resource.Status.Loading -> binding.searchResultLoading.show()
                else -> binding.searchResultLoading.hide()
            }
        })
        return binding.root
    }

    override fun onClick(event: Event) {
        val action = SearchFragmentDirections
            .actionSearchFragmentToEventDetailFragment(event.title, event.description, event.eventUrl)
        findNavController().navigate(action)
    }
}