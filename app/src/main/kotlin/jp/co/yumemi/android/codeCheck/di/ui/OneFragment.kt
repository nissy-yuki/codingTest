/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.codeCheck.di.ui

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import dagger.hilt.android.AndroidEntryPoint
import jp.co.yumemi.android.codeCheck.R
import jp.co.yumemi.android.codeCheck.databinding.FragmentOneBinding
import jp.co.yumemi.android.codeCheck.di.data.GitItem

@AndroidEntryPoint
class OneFragment : Fragment(R.layout.fragment_one) {

    private val viewModel: OneViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentOneBinding.bind(view)

        viewModel.setLanguageFormat(requireContext().getString(R.string.written_language))

        val layoutManager = LinearLayoutManager(requireContext())
        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), layoutManager.orientation)
        val adapter = CustomAdapter(object : CustomAdapter.OnItemClickListener {
            override fun itemClick(item: GitItem) {
                gotoRepositoryFragment(item)
            }
        })

        binding.searchInputText
            .setOnEditorActionListener { editText, action, _ ->
                if (action == EditorInfo.IME_ACTION_SEARCH) {

                    // キーボードを閉じる
                    val inputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE)
                    if (inputMethodManager is InputMethodManager) inputMethodManager.hideSoftInputFromWindow(editText.windowToken, InputMethodManager.RESULT_UNCHANGED_SHOWN)

                    editText.text.toString().let {
                        viewModel.searchResults(it)
                    }
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }

        // searchResultの更新を検知してRecyclerViewを更新
        viewModel.searchResult.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            if(it.isEmpty()){
                AlertDialog.Builder(requireContext()) // FragmentではActivityを取得して生成
                    .setTitle("Github Repository")
                    .setMessage("検索したリポジトリは見つかりませんでした")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }

        binding.recyclerView.also {
            it.layoutManager = layoutManager
            it.addItemDecoration(dividerItemDecoration)
            it.adapter = adapter
        }
    }

    fun gotoRepositoryFragment(item: GitItem) {
        val action = OneFragmentDirections
            .actionRepositoriesFragmentToRepositoryFragment(gitItem = item)
        findNavController().navigate(action)
    }
}
