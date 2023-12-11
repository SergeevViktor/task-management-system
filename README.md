## task-management-system

### **_Инструкция по развертыванию проекта:_**
1. [x] скачать данный репозиторий
2. [x] mvn clean package
3. [x] mvn install package -DskipTests=true
4. [x] docker-compose build
5. [x] docker-compose up -d
6. [x] сервис: http://localhost:8080



### Добавленные контроллеры:

#### AuthController:
``` POST /authorization - Авторизация 'получение jwt' ```

``` POST /registration - Регистрация новых пользователей```

#### Controller:
``` GET / – Домашняя страница ```

``` GET /admin – Страница для администратора ```

``` GET /info - Получение инфорамции по авторизованному пользователю ```

#### TaskController:
``` POST /task/create – Создание задачи ```

``` PUT /update/{taskId} – Обновление основной информации у задачи ```

``` PUT /update-executor/{taskId} - Назначение исполнителя задачи ```

``` PUT /update-status/{taskId} - Обновление статуса задачи ```

``` PUT /update-status-by-executor/{taskId} - Обновление статуса задачи исполнителем ```

``` DELETE /delete/{taskId} - Удаление задачи и всех ее комментариев ```

``` GET /view/{taskId} - Просмотр задачи ```

``` GET /view-user-tasks?from={from}&size={size} - Просмотр задач авторизованного пользователя с пагинацией ```

``` GET /view-user-tasks/{userId}?from={from}&size={size} - Просмотр задач определенного пользователя с пагинацией ```

#### CommentController:
``` POST /comment/create/{taskId} – Создание комменатрия для определенной задачи ```

``` DELETE /comment/delete-comment/{taskId} - Удаление комментария ```