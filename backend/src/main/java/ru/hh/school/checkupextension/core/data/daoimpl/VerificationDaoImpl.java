package ru.hh.school.checkupextension.core.data.daoimpl;

import ru.hh.school.checkupextension.core.data.dao.VerificationDao;
import ru.hh.school.checkupextension.core.data.entity.Verification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Класс VerificationDaoImpl, который реализует интерфейс VerificationDao,
 * и используется в VerificationService вместо VerificationRepository.
 */
public class VerificationDaoImpl implements VerificationDao {

  private static Map<Long, Verification> verifications = new HashMap<>();
  private static AtomicLong idCounter = new AtomicLong(1);

  @Override
  public Verification getById(Long id) {
    return verifications.get(id);
  }

  @Override
  public List<Verification> getAll() {
    return new ArrayList<>(verifications.values());
  }

  @Override
  public Verification create(Verification verification) {
    Long id = idCounter.getAndIncrement();
    verification.setId(id);
    verifications.put(id, verification);
    return verification;
  }

  @Override
  public Verification update(Verification verification) {
    if (verifications.containsKey(verification.getId())) {
      verifications.put(verification.getId(), verification);
      return verification;
    } else {
      return null;
    }
  }

  @Override
  public void delete(Long id) {
    verifications.remove(id);
  }
}
