package com.kalex.melichallenge.search.presentation.searchResult.xml

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.kalex.melichallenge.R
import com.kalex.melichallenge.commons.ErrorType
import com.kalex.melichallenge.commons.FormatCurrencyUseCase
import com.kalex.melichallenge.commons.ViewModelUiState
import com.kalex.melichallenge.commons.composables.KalexEmptyScreen
import com.kalex.melichallenge.commons.composables.KalexErrorScreen
import com.kalex.melichallenge.commons.composables.KalexLoadingIndicator
import com.kalex.melichallenge.commons.epoxy.epoxySearchResultItem
import com.kalex.melichallenge.databinding.FragmentSearchResultBinding
import com.kalex.melichallenge.navigation.NavigationViewModel
import com.kalex.melichallenge.search.model.dto.Result
import com.kalex.melichallenge.search.presentation.SearchViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SearchResultFragment : Fragment() {

    private var _binding: FragmentSearchResultBinding? = null
    private val binding get() = _binding!!

    private val searchViewModel by viewModel<SearchViewModel>()
    private val navigationViewModel by activityViewModel<NavigationViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchResultBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments?.let { SearchResultFragmentArgs.fromBundle(it) }
        searchViewModel.searchProduct(args?.query ?: " ")
        handleViewModelState()

    }

    private fun setEpoxyModels(data: List<Result>) {
        setComposeLoading(false)
        binding.SearchResultEpoxyRecyclerView.withModels {
            data.forEach { result: Result ->
                epoxySearchResultItem {
                    id(result.id)
                    titleText(result.title)
                    priceText(
                        FormatCurrencyUseCase.format(
                            value = result.price,
                            currency = result.currency_id
                        )
                    )
                    imgUrl(result.thumbnail)
                    onClickListener {
                        navigationViewModel.setProductDetail(result)
                        findNavController().navigate(SearchResultFragmentDirections.actionSearchResultFragmentToProductDetailFragment())
                    }
                }
            }
        }
    }

    private fun handleViewModelState() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchViewModel.searchState.collectLatest {
                    when (it) {
                        is ViewModelUiState.Empty -> {
                            setComposeEmpty()
                        }

                        is ViewModelUiState.Error -> {
                            setComposeError(it.exception)
                        }

                        is ViewModelUiState.Loading -> {
                            setComposeLoading(true)
                        }

                        is ViewModelUiState.Success -> setEpoxyModels(it.data)
                    }
                }
            }
        }
    }

    private fun setComposeEmpty() {
        binding.composeView.visibility = View.VISIBLE
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        }.setContent {
            KalexEmptyScreen(
                rationaleText = R.string.search_result_empty,
            )
        }
    }

    private fun setComposeError(exception: ErrorType) {
        binding.composeView.visibility = View.VISIBLE
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        }.setContent {
            when (exception) {
                ErrorType.INTERNET_ERROR -> KalexErrorScreen(rationaleText = R.string.search_result_internet_error)
                ErrorType.AUTHENTICATION_ERROR -> KalexErrorScreen(rationaleText = R.string.search_result_unknown_error)
                ErrorType.UNKNOWN_ERROR -> KalexErrorScreen(rationaleText = R.string.search_result_unknown_error)
            }
        }
    }

    private fun setComposeLoading(loading: Boolean) {
        if (loading) {
            binding.composeView.visibility = View.VISIBLE
            binding.composeView.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            }.setContent {
                KalexLoadingIndicator()
            }
        } else {
            binding.composeView.visibility = View.GONE
            binding.composeView.disposeComposition()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding.composeView.disposeComposition()
        _binding = null
    }
}