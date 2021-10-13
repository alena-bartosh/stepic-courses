package week_4

// Task 5.6.7
def guard(data: Any, maxLength: Int): String = {
  data match {
    case list: List[Any] if list.length <= maxLength => s"Задан список List допустимой длины"
    case list: List[Any] if list.length > maxLength => s"Длина списка больше максимально допустимого значения"
    case string: String if string.length <= maxLength => s"Длина строки не превышает максимально допустимого значения"
    case string: String if string.length > maxLength => s"Получена строка недопустимой длины"
    case _ => s"Что это? Это не строка и не список"
  }
}
