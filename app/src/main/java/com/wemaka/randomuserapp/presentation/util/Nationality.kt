package com.wemaka.randomuserapp.presentation.util

import androidx.annotation.DrawableRes
import com.wemaka.randomuserapp.R

enum class Nationality(
    val code: String,
    val fullName: String,
    @DrawableRes val flagRes: Int
) {
    AUSTRALIA("AU", "Australia", R.drawable.ic_au_australia),
    BRAZIL("BR", "Brazil", R.drawable.ic_br_brazil),
    CANADA("CA", "Canada", R.drawable.ic_ca_canada),
    SWITZERLAND("CH", "Switzerland", R.drawable.ic_ch_switzerland),
    GERMANY("DE", "Germany", R.drawable.ic_de_germany),
    DENMARK("DK", "Denmark", R.drawable.ic_dk_denmark),
    SPAIN("ES", "Spain", R.drawable.ic_es_spain),
    FINLAND("FI", "Finland", R.drawable.ic_fi_finland),
    FRANCE("FR", "France", R.drawable.ic_fr_france),
    UNITED_KINGDOM("GB", "United Kingdom", R.drawable.ic_gb_ukm_united_kingdom),
    IRELAND("IE", "Ireland", R.drawable.ic_ie_ireland),
    INDIA("IN", "India", R.drawable.ic_in_india),
    IRAN("IR", "Iran", R.drawable.ic_ir_iran),
    MEXICO("MX", "Mexico", R.drawable.ic_mx_mexico),
    NETHERLANDS("NL", "Netherlands", R.drawable.ic_nl_netherlands),
    NORWAY("NO", "Norway", R.drawable.ic_no_norway),
    NEW_ZEALAND("NZ", "New Zealand", R.drawable.ic_nz_new_zealand),
    SERBIA("RS", "Serbia", R.drawable.ic_rs_serbia),
    TURKEY("TR", "Turkey", R.drawable.ic_tr_turkey),
    UKRAINE("UA", "Ukraine", R.drawable.ic_ua_ukraine),
    UNITED_STATES("US", "United States", R.drawable.ic_us_united_states);

    companion object {
        fun fromCode(code: String): Nationality? {
            return entries.find { it.code == code }
        }

        fun getAllCodes(): List<String> {
            return entries.map { it.code }
        }

        fun getAllFullName(): List<String> {
            return entries.map { it.fullName }
        }
    }
}