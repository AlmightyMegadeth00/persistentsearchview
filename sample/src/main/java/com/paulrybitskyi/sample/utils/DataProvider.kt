package com.paulrybitskyi.sample.utils

import com.paulrybitskyi.sample.R
import com.paulrybitskyi.sample.model.User
import com.paulrybitskyi.sample.utils.extensions.random
import java.io.Serializable
import java.util.*

class DataProvider : Serializable {


    private val PROFILE_IMAGE_RESOURCE_IDS: List<Int> = listOf(
        R.drawable.ic_astronaut,    R.drawable.ic_businessman,
        R.drawable.ic_captain,      R.drawable.ic_cashier,
        R.drawable.ic_concierge,    R.drawable.ic_cooker,
        R.drawable.ic_courier,      R.drawable.ic_croupier,
        R.drawable.ic_detective,    R.drawable.ic_disc_jockey,
        R.drawable.ic_diver,        R.drawable.ic_doctor,
        R.drawable.ic_farmer,       R.drawable.ic_gentleman,
        R.drawable.ic_journalist,   R.drawable.ic_loader,
        R.drawable.ic_maid,         R.drawable.ic_manager,
        R.drawable.ic_miner,        R.drawable.ic_motorcyclist,
        R.drawable.ic_pilot,        R.drawable.ic_postman,
        R.drawable.ic_scientist,    R.drawable.ic_showman,
        R.drawable.ic_stewardess,   R.drawable.ic_taxi_driver,
        R.drawable.ic_teacher,      R.drawable.ic_waiter,
        R.drawable.ic_worker,       R.drawable.ic_writer
    )


    private var mInitialSearchQueries: MutableList<String> = mutableListOf(
        "dinosoaring",
        "liquidathor",
        "cobrawl",
        "advicewalker",
        "coachsoul",
        "lightqueen",
        "messymosquito",
        "coldox",
        "froghurt",
        "rangerman"
    )




    fun generateUsers(query: String, count: Int): List<User> {
        val random = Random()

        return MutableList(count) {
            User(it,
                "@$query$it",
                "${query.capitalize()} ${query.capitalize()}",
                (PROFILE_IMAGE_RESOURCE_IDS.random(random) ?: -1),
                random.nextBoolean(),
                random.nextBoolean()
            )
        }
    }


    fun getInitialSearchQueries(): List<String> {
        return mInitialSearchQueries
    }


    fun getSuggestionsForQuery(query: String): List<String> {
        val pickedSuggestions: MutableList<String> = mutableListOf()

        if(query.isEmpty()) {
            pickedSuggestions.addAll(mInitialSearchQueries)
        } else {
            mInitialSearchQueries.forEach {
                if(it.toLowerCase().startsWith(query.toLowerCase())) {
                    pickedSuggestions.add(it)
                }
            }
        }

        return pickedSuggestions
    }


    fun saveSearchQuery(searchQuery: String) {
        with(mInitialSearchQueries) {
            remove(searchQuery)
            add(0, searchQuery)
        }
    }


    fun removeSearchQuery(searchQuery: String) {
        mInitialSearchQueries.remove(searchQuery)
    }


}