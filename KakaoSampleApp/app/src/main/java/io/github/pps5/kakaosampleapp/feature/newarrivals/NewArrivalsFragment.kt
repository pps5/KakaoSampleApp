package io.github.pps5.kakaosampleapp.feature.newarrivals

import android.app.Activity
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import io.github.pps5.kakaosampleapp.R
import io.github.pps5.kakaosampleapp.common.vo.Resource
import io.github.pps5.kakaosampleapp.data.entity.Entry
import io.github.pps5.kakaosampleapp.databinding.FragmentHomeBinding
import org.koin.android.ext.android.inject

class NewArrivalsFragment : Fragment(), NewArrivalsAdapter.OnClickItemListener {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: NewArrivalViewModel by inject()
    private val adapter = NewArrivalsAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false).also {
            it.newArrivalsRecycler.adapter = adapter
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }
        viewModel.newArrivals.observe(this, Observer {
            when (it.status) {
                Resource.Status.Loading -> binding.newArrivalsLoading.show()
                else -> binding.newArrivalsLoading.hide()
            }
        })
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        val searchView = menu.findItem(R.id.search_box).actionView as SearchView
        setUpSearchView(searchView)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setUpSearchView(searchView: SearchView) {
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrBlank()) {
                    hideSoftKeyboard()
                    val action = NewArrivalsFragmentDirections.actionHomeFragmentToSearchFragment(query)
                    findNavController().navigate(action)
                    return true
                }
                return false
            }

            private fun hideSoftKeyboard() {
                requireActivity().let {
                    val imm = (it.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
                    imm.hideSoftInputFromWindow(it.currentFocus!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                }
            }

            override fun onQueryTextChange(newText: String?) = false
        })
    }

    override fun onClick(entry: Entry) {
        val action = NewArrivalsFragmentDirections
            .actionHomeFragmentToEventDetailFragment(entry.title, entry.summary, entry.link.url)
        findNavController().navigate(action)
    }
}