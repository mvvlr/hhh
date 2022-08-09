package com.example.newsapp.ui.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.newsapp.App
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.models.News
import java.lang.reflect.Array


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val adapter = NewsAdapter()
    private val tempArrayList = ArrayList<News>()
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var list = App.dataBase.newsDao().getAll()
        binding.recyclerView.adapter = adapter

        adapter.setList(list as ArrayList<News>)

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.newsFragment2)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
/*                tempArrayList.clear()
                val searchText = newText!!.lowercase(Locale.getDefault())
                if (searchText.isNotEmpty()) {
                    adapter.getData().forEach {
                        if (it.title.lowercase(Locale.getDefault()).contains(searchText)) {
                            tempArrayList.add(it)
                            adapter.getData()
                        }
                    }
                    adapter!!.notifyDataSetChanged()
                } else {
                    tempArrayList.clear()
                    tempArrayList.addAll(list)
                    adapter!!.notifyDataSetChanged()
                }*/
                val list = newText?.let { App.dataBase.newsDao().getSearch(it) }
                adapter.setList(list as ArrayList<News>)
                return false
            }
        })

        onClickListenerRewrite()
        onClickListenerAlert()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onClickListenerRewrite() {
        adapter.onClickListener = {
            val bundle = bundleOf("key" to it.title)
            findNavController().navigate(R.id.newsFragment2, bundle)
        }
    }

    private fun onClickListenerAlert() {
        adapter.onLongClickListener = {
            val dialog = AlertDialog.Builder(context)
            dialog.setTitle("Удалить эту новость")
            dialog.setMessage("Вы точно хотите удалить эту новость?")
            dialog.setPositiveButton("Да") { _, _ ->
                App.dataBase.newsDao().delete(adapter.getItem(it))
                adapter.deleteItemsAndNotifyAdapter(it)
                binding.recyclerView.adapter = adapter
                //Delete items in RecyclerView**

            }
            dialog.setNegativeButton("Назад") { dialog, _ -> dialog.cancel() } .create().show()

        }
        /*position ->
            val builder = android.app.AlertDialog.Builder(requireContext())
            builder.setTitle("Delete item")
            builder.setIcon(R.drawable.ic_warning)
            builder.setMessage(R.string.You_really_want_to_remove_item)
                .setPositiveButton(R.string.yes,
                    DialogInterface.OnClickListener { _, _ ->
                        App.dataBase.newsDao().delete(adapter.getItem(position))
                        adapter.deeleteItem(position)
                        adapter.notifyItemChanged(position)
                    }).setNegativeButton(R.string.no, DialogInterface.OnClickListener { _, _ ->

                })
            builder.create().show()*/
        }
    }


    /*        parentFragmentManager.setFragmentResultListener(
            "rk_news",
            viewLifecycleOwner
        ) { _, bundle ->
            val news = bundle.getSerializable("news") as News
            adapter.addItem(news)
            binding.recyclerview.adapter = adapter
        }*/
