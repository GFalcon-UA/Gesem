package ua.com.gfalcon.gesem.domain.cms.specification;

import ua.com.gfalcon.gesem.domain.norms.Work;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Oleksii Khalikov
 * @version 1.0
 * @since 1.0
 */
@Entity(name = "StagesWork")
@Table(name = "STAGES_WORKS")
public class StagesWork extends Work {
}
