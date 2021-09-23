<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Anketa</title>
</head>
<body>
<h1><%= "АНКЕТА" %>
</h1>
<form method="post" action="anketa" >
    <p>Выберите самое важное для Вас:</p>
    <div>
        <input type="radio" id="contactChoice1"
               name="contact" value="1">
        <label for="contactChoice1">Деньги</label>
    </div>
    <div>
        <input type="radio" id="contactChoice2"
               name="contact" value="2">
        <label for="contactChoice2">Знание</label>
    </div>
    <div>
        <input type="radio" id="contactChoice3"
               name="contact" value="3">
        <label for="contactChoice3">Духовность</label>
    </div>

    <p>Вы верующий человек?</p>
    <div>
        <input type="radio" id="contact2Choice1"
               name="contact2" value="1">
        <label for="contact2Choice1">Да</label>
    </div>
    <div>
        <input type="radio" id="contact2Choice2"
               name="contact2" value="2">
        <label for="contact2Choice2">Нет</label>
    </div>
    <div>
        <input type="radio" id="contact2Choice3"
               name="contact2" value="3">
        <label for="contact2Choice3">Сложно ответить</label>
    </div>
<br/>
    <div>
        <button type="submit">Submit</button>
    </div>
</form>
<br/>
<a href="hello-servlet">Нажмите сюда :)</a>
</body>
</html>