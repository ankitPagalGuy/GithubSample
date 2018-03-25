# GithubSample

Application has 2 modules -
 UI -
    It contains 3 packages
    1. Login - It contains LoginActivity which acts as a `View` and it interacts with LoginPresenter
    2. ShowUsers - It contains AllUsersActivity which acts as a `View` for showing users from github api from AllUserPresenter, 
                   intially data is fetched from api and stored in database .
    3. Search - SearchActivity has a searchbox , through which users can be searched , it interacts with SearchPresenter 
    
 Data -
    It contains all the api classes , database helper , SharedPreferences which interacts with `Presenter` 
    
Libraries Used -
  Android Architecture Components(Room,LiveData,PagingLibrary).
  -Retrofit for network requests. 
  -RX Java for asynchronus network call and database query handling.
  
I have tried to follow MVP architecture , but certainly this project can be improved further    
