package racingcar.service;

import racingcar.domain.Car;

public class OutputService {
    private final GameService gameService;

    private OutputService(final GameService gameService) {
        this.gameService = gameService;
    }

    public static OutputService from(final GameService gameService) {
        return new OutputService(gameService);
    }

    public void printEachResult() {
        for (Car car : gameService.getCars()) {
            System.out.printf("%s : %s\n", car.getName(), car.getLocation());
        }
        System.out.println();
    }

    public void printWinners() {
        System.out.print("최종 우승자 : ");
    }
}
