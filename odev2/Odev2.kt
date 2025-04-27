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

    //A sayısı Sorusu Testi
    A_sayisi("alamet")

    //Iç Açılar Sorusu Testi
    println(Ic_AcilarToplami(6))

    //Maas Hesabı Sorusu
    var saat = 21
    println("${saat} gun calisan birinin maasi ${Maas_Hesapla(saat)}")

    //Kota Sorusu Testi
    println("Toplam internet faturaniz ${Fatura(57)} TL dir")

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
    var i = 0
    var sayac = 0
    while(i<metin.length){
        var m1=metin[i]
        if(m1=='a'){
            sayac++
        }
        i++
    }
    println("a harfinden ${sayac} tane vardir.")
}

fun Ic_AcilarToplami (kenarsayisi: Int) : Int {
    return (kenarsayisi - 2) * 180
}

fun Maas_Hesapla (gun: Int) : Int {
    var saat = gun*8
    var csa = 0
    if(saat < 161){
        csa=10*saat
    }
    else {
        csa = 1600 + (saat-160) * 20
    }
    return csa
}

fun Fatura (internet: Int) : Int {
    var normal = 0
    var kalan = 0
    var sonuc = 0
    if(internet>=50) {
        normal +=100
        kalan = (internet-50) * 4
        sonuc = normal + kalan
    }
    else {
        sonuc=internet*2
    }

    return sonuc
}
