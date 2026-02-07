package org.aj.gymbuddy.lang

inline fun Boolean.then(use: () -> Unit): Boolean {
    if (this) {
        use()
    }
    return this
}

inline fun Boolean.otherwise(use: () -> Unit): Boolean {
    if (!this) {
        use()
    }
    return this
}
