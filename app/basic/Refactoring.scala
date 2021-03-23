package com.particeep.test.basic

/**
 * What is the complexity of the function ?
 * O(k*n) where k is the number of files and n is the number of categories
 * Refactor it to be a better function
 */
object Refactoring {

  case class File(
    name:     String,
    category: String
  )

  // def getCategories(files: List[File]): List[String] = {
  //   val categories: List[String] = List()

  //   if(files != null) {
  //     for(file <- files) {
  //       if(file.category != null && !categories.contains(file.category)) {
  //         categories :+ file.category
  //       }
  //     }
  //   }

  //   return categories
  // }

  //Now complexity is O(k)
  def getCategories(files: List[File]): List[String] = {
    val categories: Set[String] = Set()

    if(files != null) {
      for(file <- files) {
        if(file.category != null && !categories.contains(file.category)) {
          categories :+ file.category
        }
      }
    }

    return categories.toList
  }

}
