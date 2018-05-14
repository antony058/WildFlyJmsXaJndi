<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Weather Broker</title>
    <script src="../js/jQuery3.0.1.js"></script>
</head>
<body>

<form action="weather" method="get">
    <p>City:
        <input type="text" id="city" name="city" placeholder="Enter the city" list="cities">
        <datalist id="cities"></datalist>
    </p>
    <input type="submit" id="submitBtn" value="Submit">
</form>

<script>
    $(document).ready(function () {
        document.getElementById('city').onkeyup = function () {
            var cityName = $('#city').val();
            var isValid = checkUserInput(cityName);

            if (isValid)
                getCitiesByName(cityName);
            else
                clearDatalist();
        };

        function checkUserInput(cityName) {
            var cityNameLength = cityName.length;

            if (cityNameLength > 1) {
                return true;
            }

            return false;
        }

        function clearDatalist() {
            $('#cities').empty();
        }

        function addCitiesToDatalist(cities) {
            clearDatalist();
            for (var i=0;i<cities.length;i++) {
                $('#cities').append("<option value='" + cities[i].city + "'>");
            }
        }

        function getCitiesByName(cityName) {
            $.ajax({
                url: "/weatherapi/weather/" + cityName,
                dataType: "json",
                method: "GET",
                success: function (data) {
                    addCitiesToDatalist(data);
                }
            });
        }


    });

</script>

</body>
</html>
