public class ClientFactory {

    public static Client createClient(Person person) throws InvalidAgeException, DuplicateClientException {
        if (!person.isAboveEightTeen()) {
            throw new InvalidAgeException("Client must be at least 18 years old.");
        }
        if (ClientRegistry.getInstance().isClientRegistered(person)) {
            throw new DuplicateClientException("Client is already registered.");
        }

        return new Client(person);
    }
}
