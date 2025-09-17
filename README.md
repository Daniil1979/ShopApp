1. Избегание магических чисел.   
**Место в коде**:   
[`src/model/Product.java`](src/model/Product.java)   
public static final int MIN_RATING = 1;   
public static final int MAX_RATING = 5;   
Обоснование: вместо использования "магических" чисел 1 и 5 при проверке допустимого диапазона рейтинга, они вынесены в именованные константы, что предотвращает ошибки при ручном изменении значений и если в будущем система перейдёт на 10-балльную шкалу, изменения потребуются только в одном месте.   


2. Принцип DRY (Don't Repeat Yourself).   
**Место в коде**:   
[`src/service/OrderService.java`](src/service/OrderService.java)   
public static void validateRange(int value, int min, int max, String fieldName)   
Обоснование: метод validateRange используется в нескольких частях программы, при установке рейтинга товара, или чтобы проверить попадает ли значение в допустимый диапазон. Без этого метода такая проверка была бы продублирована в каждом месте.


3. SRP (Single Responsibility Principle).   
**Место в коде**:   
[`src/service/OrderService.java`](src/service/OrderService.java)   
Обоснование: класс OrderService отвечает исключительно за бизнес-логику заказов: создание, возврат, отслеживание статуса. Он не занимается хранением данных (это делает OrderRepository) и не взаимодействует с пользователем (это делает Main), при изменении способа хранения заказов логика сервиса не изменится.


4. OCP (Open/Closed Principle).   
**Место в коде**:   
[`src/service/RecommendationService.java`](src/service/OrderService.java)   
private RecommendationStrategy strategy = new PopularProductStrategy();   
Обоснование: RecommendationService спроектирован так, что он открыт для расширения, но закрыт для модификации. Новые стратегии могут быть добавлены через реализацию интерфейса RecommendationStrategy, без изменения кода RecommendationService, что позволит легко расширить функциональность без риска сломать логику.


5. LSP (Liskov Substitution Principle).   
**Место в коде**:   
[`src/service/PopularProductStrategy.java`](src/service/PopularProductStrategy.java)  
[`src/service/BasedOnRatingsStrategy.java`](src/service/BasedOnRatingsStrategy.java)   
Обоснование: оба класса реализуют интерфейс RecommendationStrategy и могут быть использованы взаимозаменяемо в RecommendationService. Они не изменяют условия, например не требуют дополнительных проверок, и всегда возвращают список товаров. Это гарантирует, что любой код, ожидающий RecommendationStrategy, будет корректно работать с любой его реализацией.


6. DIP (Dependency Inversion Principle).   
**Место в коде**:   
[`src/service/OrderService.java`](src/service/OrderService.java)   
private final OrderRepository orderRepository;   
Обоснование: OrderService зависит от абстракции OrderRepository, а не от конкретной реализации InMemoryOrderRepository. Это позволяет легко менять реализацию, например на DatabaseOrderRepository, не изменяя код сервиса. Благодаря чему, уровни сервиса не зависят друг от друга.