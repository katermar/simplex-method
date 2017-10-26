<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <link rel="stylesheet" href="../css/bootstrap.min.css">   		
        <script src="../js/bootstrap.min.js"></script>       
    </head>

    <body>          
        <div class="container">
            <h2>Employees</h2>
            <!--Search Form -->
            <form action="/breakfast" method="get" id="searchFood" role="form">
                <input type="hidden" id="searchAction" name="searchAction" value="searchByCalories">
                <div class="form-group col-xs-5">
                    <input type="text" name="caloriesAmount" id="caloriesAmount" class="form-control" required="true" placeholder="Type the Amount of calories"/>
                </div>
                <button type="submit" class="btn btn-info">
                    <span class="glyphicon glyphicon-search"></span> Search
                </button>
                <br>
                <br>
            </form>

            <!--com.martinkevich.app.Food List-->
            <c:if test="${not empty message}">                
                <div class="alert alert-success">
                    ${message}
                </div>
            </c:if> 
            <form action="/breakfast" method="post" id="breakfastForm" role="form" >
                <c:choose>
                    <c:when test="${not empty menu}">
                        <table  class="table table-striped">
                            <thead>
                                <tr>
                                    <td>#</td>
                                    <td>Name</td>
                                    <td>Price</td>
                                    <td>Calories</td>
                                    <td>Ingridients</td>
                                    <td>Description</td>
                                    <td></td>
                                </tr>
                            </thead>
                            <c:forEach var="food" items="${foodList}">
                                <c:set var="classSucess" value="info"/>
                                <tr class="${classSucess}">
                                    <td>${food.name}</td>
                                    <td>${food.price}</td>
                                    <td>${food.calories}</td>
                                    <td>${food.ingredients}</td>
                                    <td>${food.description}</td>
                                </tr>
                            </c:forEach>               
                        </table>  
                    </c:when>                    
                    <c:otherwise>
                        <br>           
                        <div class="alert alert-info">
                            No people found matching your search criteria
                        </div>
                    </c:otherwise>
                </c:choose>                        
            </form>
        </div>
    </body>
</html>