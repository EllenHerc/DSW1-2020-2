<html>
    <body>
        
        <table>
            <tr>
                <th>Celsius</th>
                <th>Fahrenheit</th>
            </tr>
            <% 
                String minS = request.getParameter("min");
                String maxS = request.getParameter("max");
                String stepS = request.getParameter("step");

                int min = ("".equals(minS)) ? -100 : Integer.parseInt(minS);
                int max = ("".equals(minS)) ? 100 : Integer.parseInt(maxS);
                int step = ("".equals(stepS)) ? 5 : Integer.parseInt(stepS);
            
                for (int celsius = min; celsius <= max; celsius += step) {
                    double fahr = 1.8 * celsius + 32;
            %>
                    <tr>
                        <td> <%= celsius %> </td>
                        <td> <%= fahr %> </td>
                    </tr>
                <% } %>
        </table>

    </body>
</html>
