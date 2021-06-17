package model

data class Position(val fen: FEN, val movesList: List<String>) {
//    override fun toString(): String {
//        return movesList.reduce {acc,s -> ", $s"}
//    }
}

typealias FEN = String
typealias PGN = String