package com.test03;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MTest02 {
    public static void main(String[] args) throws Exception {
        // 발급받은 액세스 토큰
        String accessToken = "eyJraWQiOiJlMGE3NTkwNi1jNGQ0LTRiMzQtOTVhZS04NGVhNDZjY2U0OTIiLCJhb"+
        		"GciOiJSUzI1NiJ9.eyJzdWIiOiJjbGllbnQiLCJhdWQiOiJjbGllbnQiLCJuYmYiOjE3NDg1MDc1MjgsIm"+
        		"lzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6OTAwMCIsImV4cCI6MTc0ODUwNzgyOCwiaWF0IjoxNzQ4NTA3NTI4"+
        		"LCJqdGkiOiJhYzg1MmMwNS1mNWJhLTQ2MTMtOTE1MC01NWRmMjBiYWM4ZjMifQ.b9ZxS8rfhXQWenHoTAp"+
        		"nV-VDmxU7MiHfnDdCHTuXPBkxPcYqkwSjvBJW8x82LPl9hsPvPDACpM1y0MV4cdmBngkVP4nqoqTeFmu8Z"+
        		"c5JlffSL_9xPG2x9upLvulj3S5N6-9yCCJFNdO7Br6K_FHNPFE2hOrqq06J9Ezi_3XGz4klnHQqGEsh5fO"+
        		"16hNRCKp5Fxyg7c-CfAAgHNQ_AloQot7ryUUME9ms264lStsye24qFtk8UmnxusaEuY0c-eF6c0JkNuPyd"+
        		"3oNQR-to3U-qtftv5BlWrxScNW_18PQOrFce59TjTEzYYmsfTuQBB5kghFhawLIno7ybeaVUp4yNw";

       
        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI("http://localhost:9000/v1/users/me"))
            .header("Authorization", "Bearer " + accessToken)
            .GET()
            .build();

       
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

       
        System.out.println("응답 코드: " + response.statusCode());
        System.out.println("응답 내용: " + response.body());
    }
}