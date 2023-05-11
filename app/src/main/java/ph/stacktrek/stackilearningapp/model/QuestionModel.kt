package ph.stacktrek.stackilearningapp.model

class QuestionModel {
    var question: String? = null
    var option1: String? = null
    var option2: String? = null
    var option3: String? = null
    var option4: String? = null
    var answerQ: Int = 0
    var imagePath: Int = 0

    constructor()

    constructor(question: String?, option1: String?, option2: String?, option3: String?, option4: String?, answerQ: Int, imagePath: Int) {
        this.question = question
        this.option1 = option1
        this.option2 = option2
        this.option3 = option3
        this.option4 = option4
        this.answerQ = answerQ
        this.imagePath = imagePath
    }
}
