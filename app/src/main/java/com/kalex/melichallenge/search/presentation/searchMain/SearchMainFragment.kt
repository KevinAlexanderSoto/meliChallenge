package com.kalex.melichallenge.search.presentation.searchMain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kalex.melichallenge.search.presentation.searchResult.xml.SearchResultFragmentArgs

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SearchMainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                SearchMainScreen(onSearchProduct = { query ->
                    val bundle =
                        SearchMainFragmentDirections.actionSearchMainFragmentToSearchResultFragment(
                            query
                        )
                    findNavController().navigate(bundle)
                })
            }
        }
    }
}