package com.example.presentation.model

enum class BookCategory(val id: Int, val categoryName: String) {
    All(100, "전체"),
    Fiction(101, "소설"),
    Poetry(102, "시 / 에세이"),
    Art(103, "예술 / 대중문화"),
    SocialScience(104, "사회과학"),
    History(105, "역사와 문화"),
    Magazine(107, "잡지"),
    Comics(108, "만화"),
    Baby(109, "유아"),
    Child(110, "아동"),
    Home(111, "가정과 생활"),
    Youth(112, "청소년"),
    Elementary(113, "초등학습서"),
    MiddleSchool(129, "중등학습서"),
    HighSchool(114, "고등학습서"),
    Dictionary(115, "국어 / 외국어 / 사전"),
    Science(116, "자연과 과학"),
    Economy(117, "경제경영"),
    SelfImprovement(118, "자기계발"),
    Humanities(119, "인문"),
    Religion(120, "종교 / 역학"),
    Computer(122, "컴퓨터 / 인터넷"),
    Exam(123, "자격서 / 수험서"),
    Hobby(124, "취미 / 레저"),
    University(125, "전공도서 / 대학교재"),
    Health(126, "건강 / 뷰티"),
    Trip(128, "여행")
}