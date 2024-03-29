<h1>Page objects creation description</h1>

Классы страниц форм и попапов должны содержать несколько соответствующие пункты перечисленные ниже, 
следует отметить, что классы должны содержать перечисленные особенности в указанном в списках порядке:
<h3>Page class</h2>
<p></p>
<ul>
 <li>Наследоваться от <b>BasePage</b></li>
 <li>private static final String MAIN_ELEMENT_LOC- поле с локатором до основного элемента текущей страницы
 </li>
 <li>Константы с паттернами для поиска элементов, например, если несколько элементов имеют одинаковый локатор, 
  и отличаются лишь текстом, или ссылкой или прочим</li>
 <li>Ноль или более элементов найденных от основного по подобию: 
 private SelenideElement <b>nameOfTheElement</b> getMainElement().<b>specificElement</b>
 </li>
 <li>Конструктор, который вызывает конструктор родительского класса, и инициализирует основной элемент 
 через указанный локатор для основного элемента
 </li>
 <li>Публичный геттеры форм и попапов, которые принадлежат странице
 </li>
 <li>Методы по взаимодействию со страницей
 </li>
</ul>

<h3>Form class</h3>
<p></p>
<ul>
 <li>Наследоваться от <b>PageObject</b></li>
 <li>private static final String MAIN_ELEMENT_LOC- поле с локатором до основного элемента текущей формы
 </li>
 <li>Константы с паттернами для поиска элементов, например, если несколько элементов имеют одинаковый локатор, 
  и отличаются лишь текстом, или ссылкой или прочим</li>
 <li>Ноль или более элементов найденных от основного по подобию: 
 private SelenideElement <b>nameOfTheElement</b> getMainElement().<b>specificElement</b>
 </li>
 <li>Конструктор, который вызывает конструктор родительского класса, и инициализирует основной элемент 
 через переданный родительский элемент и указанный локатор для основного элемента
 </li>
 <li>Публичный геттеры форм и попапов, которые принадлежат форме
 </li>
 <li>Методы по взаимодействию с формой
 </li>
</ul>

<h3>Modal class</h3>
<p></p>
<ul>
 <li>Наследоваться от <b>PageObject</b></li>
 <li>private static final String MAIN_ELEMENT_LOC- поле с локатором до основного элемента текущего попапа
 </li>
 <li>Константы с паттернами для поиска элементов, например, если несколько элементов имеют одинаковый локатор, 
  и отличаются лишь текстом, или ссылкой или прочим</li>
 <li>Ноль или более элементов найденных от основного по подобию: 
 private SelenideElement <b>nameOfTheElement</b> getMainElement().<b>specificElement</b>
 </li>
 <li>Конструктор, который вызывает конструктор родительского класса, и инициализирует основной элемент 
 через указанный локатор для основного элемента
 </li>
 <li>Публичный геттеры форм и попапов, которые принадлежат попапу
 </li>
 <li>Методы по взаимодействию с попапом
 </li>
</ul>