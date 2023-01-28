##curl samples (application deployed at application context `vote`).
> For windows use `Git Bash`
> and `cmd` in win10 v.1803+

##Users Rest Controller
|Method|Path|Role|Description
|---|---|---|---|
|GET|`/rest/admin/users`|admin|Get all users
curl http://localhost:8080/vote/rest/admin/users

|Method|Path|Role|Description
|---|---|---|---|
|GET|`/rest/admin/users/{id}`|admin|Get user by id
curl http://localhost:8080/vote/rest/admin/users/100001

|Method|Path|Role|Description
|---|---|---|---|
|GET|`/rest/admin/users/by-email`|admin|Get user by e-mail
curl http://localhost:8080/vote/rest/admin/users/by-email?email=maria@mail.ru

|Method|Path|Role|Description
|---|---|---|---|
|POST|`/rest/admin/users`|admin|Create user
####-in Git Bash
curl -s -X POST -d '{"name":"NewUser123", "email":"user123@mail.ru", "password":"password", "roles":["USER"]}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/vote/rest/admin/users
####-in win cmd
curl -s -X POST -d "{\"name\":\"NewUser123\", \"email\":\"user123@mail.ru\", \"password\":\"password\", \"roles\":[\"USER\"]}" -H "Content-Type:application/json;charset=UTF-8" http://localhost:8080/vote/rest/admin/users

|Method|Path|Role|Description
|---|---|---|---|
|DELETE|`/rest/admin/users/{id}`|admin|Delete user
curl -X DELETE http://localhost:8080/vote/rest/admin/users/100001

##Votes Rest Controller
|Method|Path|Role|Description
|---|---|---|---|
|GET|`/rest/admin/users/votes`|admin|Get all votes
curl http://localhost:8080/vote/rest/admin/users/votes

|Method|Path|Role|Description
|---|---|---|---|
|GET|`/rest/admin/users/{userId}/votes`|admin|Get all votes by user
|GET|`/rest/profile/votes`|user|Get all votesTo
curl http://localhost:8080/vote/rest/admin/users/100001/votes
curl http://localhost:8080/vote/rest/profile/votes

|Method|Path|Role|Description
|---|---|---|---|
|GET|`/rest/admin/users/{userId}/votes/{id}`|admin|Get all votes by user in id
|GET|`/rest/profile/votes/{id}`|user|Get vote
curl http://localhost:8080/vote/rest/admin/users/100001/votes/100020
curl http://localhost:8080/vote/rest/profile/votes/100019

|Method|Path|Role|Description
|---|---|---|---|
|DELETE|`/rest/admin/users/{userId}/votes/{id}`|admin|Delete vote
|DELETE|`/rest/profile/votes/{id}`|user|Delete vote
curl -X DELETE http://localhost:8080/vote/rest/admin/users/100001/votes/100020
curl -X DELETE http://localhost:8080/vote/rest/profile/votes/100019

|Method|Path|Role|Description
|---|---|---|---|
|POST|`/rest/profile/votes`|user|Create vote
curl -s -X POST http://localhost:8080/vote/rest/profile/votes?restaurantId=100005

##Restaurants Rest Controller
|Method|Path|Role|Description
|---|---|---|---|
|GET|`/rest/admin/restaurants`|admin|Get all restaurants
|GET|`/rest/profile/restaurants`|user|Get all restaurants
curl http://localhost:8080/vote/rest/admin/restaurants
curl http://localhost:8080/vote/rest/profile/restaurants

|Method|Path|Role|Description
|---|---|---|---|
|GET|`/rest/admin/restaurants/{id}`|admin|Get restaurant
|GET|`/rest/profile/restaurants/{id}`|user|Get restaurant
curl http://localhost:8080/vote/rest/admin/restaurants/100005
curl http://localhost:8080/vote/rest/profile/restaurants/100005

|Method|Path|Role|Description
|---|---|---|---|
|POST|`/rest/admin/restaurants`|admin|Create restaurant
####-in win cmd
curl -s -X POST -d "{\"name\":\"New rest\"}" -H "Content-Type:application/json;charset=UTF-8" http://localhost:8080/vote/rest/admin/restaurants

|Method|Path|Role|Description
|---|---|---|---|
|PUT|`/rest/admin/restaurants/{id}`|admin|Update restaurant
####-in win cmd
curl -s -X PUT -d "{\"id\":\"100005\", \"name\":\"UPDATED\"}" -H "Content-Type:application/json;charset=UTF-8" http://localhost:8080/vote/rest/admin/restaurants/100005

|Method|Path|Role|Description
|---|---|---|---|
|DELETE|`/rest/admin/restaurants/{id}`|admin|Delete restaurant
curl -s -X DELETE http://localhost:8080/vote/rest/admin/restaurants/100006

##Dish Rest Controller
|Method|Path|Role|Description
|---|---|---|---|
|GET|`/rest/admin/restaurants/{restaurantId}/dishes/{id}`|admin|Get dish
|GET|`/rest/profile/restaurants/{restaurantId}/dishes/{id}`|user|Get dish
curl http://localhost:8080/vote/rest/admin/restaurants/100004/dishes/100007
curl http://localhost:8080/vote/rest/profile/restaurants/100004/dishes/100007

|Method|Path|Role|Description
|---|---|---|---|
|GET|`/rest/admin/restaurants/{restaurantId}/dishes`|admin|Get all dishes by restaurant
curl http://localhost:8080/vote/rest/admin/restaurants/100004/dishes

|Method|Path|Role|Description
|---|---|---|---|
|GET|`/rest/admin/restaurants/{restaurantId}/dishes/today`|admin|Get dishes by restaurant today
|GET|`/rest/profile/restaurants/{restaurantId}/dishes`|user|Get dishes by restaurant today
curl http://localhost:8080/vote/rest/admin/restaurants/100004/dishes/today
curl http://localhost:8080/vote/rest/profile/restaurants/100004/dishes

|Method|Path|Role|Description
|---|---|---|---|
|GET|`/rest/admin/restaurants/{restaurantId}/dishes/by-date`|admin|Get dishes by restaurant and date
curl http://localhost:8080/vote/rest/admin/restaurants/100004/dishes/by-date?date=2022-01-01

|Method|Path|Role|Description
|---|---|---|---|
|POST|`/rest/admin/restaurants/{restaurantId}/dishes`|admin|Add dish
curl -s -X POST -d "{\"name\":\"New Dish 321\", \"price\":\"321\"}" -H "Content-Type:application/json;charset=UTF-8" http://localhost:8080/vote/rest/admin/restaurants/100004/dishes

|Method|Path|Role|Description
|---|---|---|---|
|PUT|`/rest/admin/restaurants/{restaurantId}/dishes/{id}`|admin|Update dish
curl -s -X PUT -d "{\"id\":\"100008\", \"name\":\"_UPDATED_\", \"price\":\"555\"}" -H "Content-Type:application/json;charset=UTF-8" http://localhost:8080/vote/rest/admin/restaurants/100004/dishes/100008

|Method|Path|Role|Description
|---|---|---|---|
|DELETE|`/rest/admin/restaurants/{restaurantId}/dishes/{id}`|admin|Delete dish
curl -s -X DELETE http://localhost:8080/vote/rest/admin/restaurants/100004/dishes/100008