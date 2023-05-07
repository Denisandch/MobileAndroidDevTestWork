package com.ntpro.mobileandroiddevtestwork

enum class Column(val columnNameUI: String, val columnNameDB: String) {
    TIME_STAMP("Время сделки", "timeStamp"),
    INSTRUMENT_NAME("Название инструмента", "instrumentName"),
    PRICE("Цена сделки", "price"),
    AMOUNT("Объем сделки", "amount"),
    SIDE("Тип сделки", "side");

    fun test() {
        
    }
    companion object {
        fun fromColumnName(columnName: String): Column? {
            return values().find { it.columnNameUI == columnName }
        }
    }
}