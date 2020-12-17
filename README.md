
<h1 align="center"> Weather Forecast in Brasov County with Servlet</h1>

<h2>Application description :</h2>

* Application, created using Java Servlet, through which a user sees the weather forecast (temperature, pressure, chances of rain, etc.) for the next 24 hours, from a certain location.
* I used only servlet, on a **Tomcat server**, without database.

<h3>Functionalities:</h3>

- [X] the user can search the weather forecast by :
1. **location name** (the server will respond with the location or locations that best match the name entered, from where the user will choose the location they are looking for);
2. **GPS coordinates** (the server will respond with the nearest location or locations, from where the user will choose the location they are looking for).

- [X] the weather forecast is updated from minute by minute.

<h2>The start page design :</h2>

![image](https://user-images.githubusercontent.com/58684695/101262674-1c304d80-3749-11eb-82aa-4ecd939472f6.png)

<h4> 1. If you want to see weather forecast in a locality entering the name of this locality</h4>
<h5> Example: entering - BR</h5>
<p align="center">
<img width="600" alt="Screenshot_1" src="https://user-images.githubusercontent.com/58684695/101654779-8e56aa00-3a49-11eb-805a-286b8688e9c1.png">
</p>
- if in the list of localities there is "BR" as substring, all those localities that contain "BR" in name are displayed :
<p align="center">
<img width="600" alt="Screenshot_2" src="https://user-images.githubusercontent.com/58684695/101656029-e9d56780-3a4a-11eb-9d5d-49fa8af1dcca.png">
</p>

<h4> 2. If you want to see weather forecast in a locality entering the coordinates of this locality</h4>
<h5> Example: entering - Latitude: 45.3456 Longitude: 24.6789</h5>
<p align="center">
<img width="600" alt="Screenshot_4" src="https://user-images.githubusercontent.com/58684695/101657207-4be29c80-3a4c-11eb-9937-8d216c6f8410.png">
</p>
- if the entered coordinates do not match those already in the list of localities, the closest locality to these entered coordinates will be found :
<p align="center">
<img width="600" align="center" alt="Screenshot_5" src="https://user-images.githubusercontent.com/58684695/101657272-5b61e580-3a4c-11eb-9322-242601e050a7.png">
</p>

<h2>The page where the weather forecasts are displayed:</h2>

![img](https://user-images.githubusercontent.com/58684695/102120114-bd568c80-3e4a-11eb-9659-9e0c53ac27c4.png)
