package com.chooongg.form.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chooongg.form.addText
import com.chooongg.form.app.databinding.FragmentHomeBinding
import com.google.android.material.shape.MaterialShapeDrawable

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.appBarLayout.addLiftOnScrollListener { _, backgroundColor ->
            activity?.window?.statusBarColor = backgroundColor
        }
        binding.formView.setData {
            addPart {
                addText("Text", "asdfasdfasdf") {
                    content = "asdfasdfasdf"
                }
                addText("Text", "asdfasdfasdf") {
                    content = "asdfasdfasdf"
                }
                addText("Text", "asdfasdfasdf") {
                    content = "asdfasdfasdf"
                }
                addText("Text", "asdfasdfasdf") {
                    content = "asdfasdfasdf"
                }
            }
            addPart {
                addText("Text", "asdfasdfasdf") {
                    content = "asdfasdfasdf"
                }
                addText("Text", "asdfasdfasdf") {
                    content = "asdfasdfasdf"
                }
                addText("Text", "asdfasdfasdf") {
                    content = "asdfasdfasdf"
                }
                addText("Text", "asdfasdfasdf") {
                    content = "asdfasdfasdf"
                }
            }
        }

//        3    0 1 2
//
//        5    0 1 2 3 4
//             3   *
//        8    0 1 2 3 4 5 6 7
//
//
//        5
//
//        2  2
    }

    override fun onResume() {
        super.onResume()
        val color =
            (binding.appBarLayout.background as? MaterialShapeDrawable)?.fillColor?.defaultColor
        if (color != null) {
            activity?.window?.statusBarColor = color
        }
    }
}