package com.example.kotlinodevleri.odev2

fun main() {
    //Fahrenhiet Testi
    var a = 4;
    println("$a derecenin fahrenheiti: ${FahrenaytDonustur(a)}")

    //Cevre Sorusu Testi
    CevreHesapla(2,3)

    //Faktoriyel Sorusu Testi
    val s1 = 7
    println("${s1} sayisinin faktoriyeli: ${FaktoriyelHesapla(s1)}")

}
fun FahrenaytDonustur (derece : Int) : Double {
    return derece * 1.8 +32
}

fun CevreHesapla ( kenar1: Int, kenar2: Int) {
    println("Cevre: ${2*(kenar1+kenar2)}")
}

fun FaktoriyelHesapla (sayi: Int) : Int {
    if(sayi==1){
        return 1
    }
    return sayi * FaktoriyelHesapla(sayi-1)
}

fun A_sayisi (metin: String) {
    for(i in )
}