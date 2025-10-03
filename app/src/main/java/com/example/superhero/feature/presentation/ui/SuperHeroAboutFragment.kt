package com.example.superhero.feature.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.superhero.R
import com.example.superhero.core.presentation.AppIntent
import com.example.superhero.core.presentation.views.ErrorAppFactory
import com.example.superhero.feature.domain.model.SuperHero
import com.example.superhero.feature.presentation.ui.adapter.SuperHeroAdapter
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SuperHeroAboutFragment : BottomSheetDialogFragment() {

    private val superHeroAboutViewModel: SuperHeroAboutViewModel by viewModel()
    private var _binding: FragmentSuperheroListBinding? = null
    private val binding get() = _binding!!
    private val superHeroAdapter = SuperHeroAdapter()
    private lateinit var appIntent: AppIntent
    private lateinit var skeleton: Skeleton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSuperheroListBinding.inflate(inflater, container, false)
        appIntent = AppIntent(requireContext())
        setupView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        superHeroAboutViewModel.viewSuperHeroes()
    }

    private fun setupView() {
        binding.recyclerViewSuperhero.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = superHeroAdapter
            skeleton = applySkeleton(R.layout.item_superhero)
            superHeroAdapter.setEvent { superHero -> openSuperHeroDetail(superHero) }
        }
    }

    private fun setupObserver() {
        superHeroAboutViewModel.uiState.observe(viewLifecycleOwner, Observer { uiState ->
            uiState.superHeroes?.let { bindData(it) }

            uiState.errorMessage?.let {
                val errorAppUI = ErrorAppFactory(requireContext()).build(it) {
                    superHeroAboutViewModel.viewSuperHeroes()
                }
                binding.errorAppView.render(errorAppUI)
            } ?: run {
                binding.errorAppView.hide()
            }

            if (uiState.isLoading) {
                skeleton.showSkeleton()
            } else {
                skeleton.showOriginal()
            }
        })
    }

    private fun bindData(superHeroes: List<SuperHero>) {
        superHeroAdapter.submitList(superHeroes.sortedBy { it.id })
    }

    private fun openSuperHeroDetail(superHero: SuperHero) {
        // TODO: implementar navegación al detalle del superhéroe
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
