/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.codeCheck.di.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import jp.co.yumemi.android.codeCheck.R
import jp.co.yumemi.android.codeCheck.di.ui.TopActivity.Companion.lastSearchDate
import jp.co.yumemi.android.codeCheck.databinding.FragmentTwoBinding

@AndroidEntryPoint
class TwoFragment : Fragment(R.layout.fragment_two) {

    private val args: TwoFragmentArgs by navArgs()

    private var _binding: FragmentTwoBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("検索した日時", lastSearchDate.toString())

        _binding = FragmentTwoBinding.bind(view)
        val item = args.gitItem

        binding.ownerIconView.load(item.ownerIconUrl){
            error(R.drawable.ic_launcher_foreground)
        }
        binding.nameView.text = item.name
        binding.languageView.text = requireContext().getString(R.string.written_language).format(item.language)
        binding.starsView.text = "${item.stargazersCount} stars"
        binding.watchersView.text = "${item.watchersCount} watchers"
        binding.forksView.text = "${item.forksCount} forks"
        binding.openIssuesView.text = "${item.openIssuesCount} open issues"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
