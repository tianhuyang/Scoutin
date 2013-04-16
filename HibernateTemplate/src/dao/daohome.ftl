${pojo.getPackageDeclaration()}
// Generated ${date} by Hibernate Tools ${version}

<#assign classbody>
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>/**
 * Home object for domain model class ${declarationName}.
 * @see ${pojo.getQualifiedDeclarationName()}
 * @author Hibernate Tools
 */
<#if ejb3>
@${pojo.importType("javax.ejb.Stateless")}
</#if>
<#assign DaoUtils = pojo.importType("com.scoutin.utilities.DaoUtils")>
<#assign classModel = "org.hibernate.type.ForeignKeyDirection">
<#assign dummy = pojo.importType("org.hibernate.Query")>
public class ${declarationName}Home {

    private static final ${pojo.importType("org.apache.commons.logging.Log")} log = ${pojo.importType("org.apache.commons.logging.LogFactory")}.getLog(${pojo.getDeclarationName()}Home.class);
    private final String ${clazz.identifierProperty.name}sExistHql = "select count(className) from ${declarationName} className where className.${clazz.identifierProperty.name} in :${clazz.identifierProperty.name}s";

<#if ejb3>
    @${pojo.importType("javax.persistence.PersistenceContext")} private ${pojo.importType("javax.persistence.EntityManager")} entityManager;
    
    public void persist(${declarationName} transientInstance) {
        log.debug("persisting ${declarationName} instance");
        try {
            entityManager.persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void remove(${declarationName} persistentInstance) {
        log.debug("removing ${declarationName} instance");
        try {
            entityManager.remove(persistentInstance);
            log.debug("remove successful");
        }
        catch (RuntimeException re) {
            log.error("remove failed", re);
            throw re;
        }
    }
    
    public ${declarationName} merge(${declarationName} detachedInstance) {
        log.debug("merging ${declarationName} instance");
        try {
            ${declarationName} result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
<#if clazz.identifierProperty?has_content>    
    public ${declarationName} findById( ${pojo.getJavaTypeName(clazz.identifierProperty, jdk5)} id) {
        log.debug("getting ${declarationName} instance with id: " + id);
        try {
            ${declarationName} instance = entityManager.find(${pojo.getDeclarationName()}.class, id);
            log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
</#if>
<#else> 
<#if false>   
<!--    private final ${pojo.importType("org.hibernate.SessionFactory")} sessionFactory = getSessionFactory();
    
    protected ${pojo.importType("org.hibernate.SessionFactory")} getSessionFactory() {
        try {
            return (${pojo.importType("org.hibernate.SessionFactory")}) new ${pojo.importType("javax.naming.InitialContext")}().lookup("${sessionFactoryName}");
        }
        catch (Exception e) {
            log.error("Could not locate SessionFactory in JNDI", e);
            throw new IllegalStateException("Could not locate SessionFactory in JNDI");
        }
    }
-->
</#if>
    
    public void persist(${declarationName} transientInstance) {
        log.debug("persisting ${declarationName} instance");
        try {
            ${DaoUtils}.sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void save(${declarationName} transientInstance) {
        log.debug("saving ${declarationName} instance");
        try {
            ${DaoUtils}.sessionFactory.getCurrentSession().save(transientInstance);
            log.debug("saving successful");
        }
        catch (RuntimeException re) {
            log.error("saving failed", re);
            throw re;
        }
    }
    
    public void attachDirty(${declarationName} instance) {
        log.debug("attaching dirty ${declarationName} instance");
        try {
            ${DaoUtils}.sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(${declarationName} instance) {
        log.debug("attaching clean ${declarationName} instance");
        try {
            ${DaoUtils}.sessionFactory.getCurrentSession().lock(instance, ${pojo.importType("org.hibernate.LockMode")}.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(${declarationName} persistentInstance) {
        log.debug("deleting ${declarationName} instance");
        try {
            ${DaoUtils}.sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void evict(${declarationName} persistentInstance) {
        log.debug("evicting ${declarationName} instance");
        try {
            ${DaoUtils}.sessionFactory.getCurrentSession().evict(persistentInstance);
            log.debug("evicting successful");
        }
        catch (RuntimeException re) {
            log.error("evicting failed", re);
            throw re;
        }
    }
    
    public ${declarationName} merge(${declarationName} detachedInstance) {
        log.debug("merging ${declarationName} instance");
        try {
            ${declarationName} result = (${declarationName}) ${DaoUtils}.sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
<#if clazz.identifierProperty?has_content>
    public ${declarationName} findById( ${c2j.getJavaTypeName(clazz.identifierProperty, jdk5)} id) {
        log.debug("getting ${declarationName} instance with id: " + id);
        try {
            ${declarationName} instance = (${declarationName}) ${DaoUtils}.sessionFactory.getCurrentSession()
                    .get("${clazz.entityName}", id);
            if (instance==null) {
                log.debug("get successful, no instance found");
            }
            else {
                log.debug("get successful, instance found");
            }
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public ${declarationName} load( ${c2j.getJavaTypeName(clazz.identifierProperty, jdk5)} id) {
        log.debug("loading ${declarationName} instance with id: " + id);
        try {
            ${declarationName} instance = (${declarationName}) ${DaoUtils}.sessionFactory.getCurrentSession()
                    .load("${clazz.entityName}", id);
            if (instance==null) {
                log.debug("load successful, no instance found");
            }
            else {
                log.debug("load successful, instance found");
            }
            return instance;
        }
        catch (RuntimeException re) {
            log.error("load failed", re);
            throw re;
        }
    }
    
	public boolean hasAll(${c2j.getJavaTypeName(clazz.identifierProperty, jdk5)}[] ${clazz.identifierProperty.name}s) {
		log.debug("${declarationName} hasAll");
		boolean hasAll = false;
		try {
			Query query = DaoUtils.sessionFactory.getCurrentSession()
					.createQuery(${clazz.identifierProperty.name}sExistHql);
			query.setParameterList("${clazz.identifierProperty.name}s", ${clazz.identifierProperty.name}s);
			Long count = (Long) query.iterate().next();
			hasAll = count == ${clazz.identifierProperty.name}s.length;
			log.debug("hasAll successful");
		} catch (RuntimeException re) {
			log.error("hasAll failed", re);
			throw re;
		}
		return hasAll;
	}	
</#if>

<#foreach field in pojo.getAllPropertiesIterator()>
<#if field.type.isEntityType() && (field.type.getForeignKeyDirection() != "toParent")>
<#assign entityName = field.type.getName()>
<#assign rclazz = cfg.getClassMapping(entityName)>
<#assign rClassName = pojo.importType(rclazz.getClassName())>
<#assign rIdName = rclazz.identifierProperty.name>
<#assign rbIdName = rIdName.substring(0,1).toUpperCase() + rIdName.substring(1)>
<#assign rIdType = c2j.getJavaTypeName(rclazz.identifierProperty, jdk5)>
<#assign idName = clazz.identifierProperty.name>
<#assign ok = true>
<#foreach column in clazz.getIdentifierProperty().getColumnIterator()>
    <#foreach col in field.getColumnIterator()>
    <#if col == column>
    <#assign ok = false>
    </#if>
    </#foreach>
</#foreach>
<#if ok == true>
    private final String ${rIdName}Hql = "select a.${field.name}.${rIdName} from ${declarationName} a where a.${idName} = :${idName}";
	
	public ${rIdType} get${rbIdName}Id(${c2j.getJavaTypeName(clazz.identifierProperty, jdk5)} ${idName}) {
		log.debug("get${rbIdName}Id with ${idName}" + ${idName});
		${rIdType}  ${rIdName};
		try {
			Query query = DaoUtils.sessionFactory.getCurrentSession().createQuery(${rIdName}Hql);
			query.setParameter("${idName}", ${idName}); 
			${rIdName} = (${rIdType})query.uniqueResult();
			log.debug("get${rbIdName}Id successful");
			return  ${rIdName};
		} catch (RuntimeException re) {
			log.error("get${rbIdName}Id failed", re);
			throw re;
		}	
	}
</#if>
</#if>

<#if field.name.endsWith("Count")>
<#assign Name = field.name.substring(0,1).toUpperCase() + field.name.substring(1)>
<#assign idName = clazz.identifierProperty.name>
<#assign idType = c2j.getJavaTypeName(clazz.identifierProperty, jdk5)>
<#assign className = pojo.importType(clazz.getClassName())>
private final String increase${Name}Hql = "update ${className} a set a.${field.name} = a.${field.name} + :count where a.${idName} =:${idName}";

	public void increase${Name}(${idType} ${idName}, int count) {
		log.debug("increase${Name} with ${idName}:" + ${idName});
		try {
			Query query = DaoUtils.sessionFactory.getCurrentSession()
					.createQuery(increase${Name}Hql);
			query.setParameter("${idName}", ${idName});
			query.setParameter("count", count);
			query.executeUpdate();
			log.debug("increase${Name} successful");
		} catch (RuntimeException re) {
			log.error("increase${Name} failed", re);
			throw re;
		}
	}
</#if>	
</#foreach> 

<#if clazz.hasNaturalId()>
    public ${declarationName} findByNaturalId(${c2j.asNaturalIdParameterList(clazz)}) {
        log.debug("getting ${declarationName} instance by natural id");
        try {
            ${declarationName} instance = (${declarationName}) ${DaoUtils}.sessionFactory.getCurrentSession()
                    .createCriteria("${clazz.entityName}")
<#if jdk5>
                    .add( ${pojo.staticImport("org.hibernate.criterion.Restrictions", "naturalId")}()
<#else>
                   .add( ${pojo.importType("org.hibernate.criterion.Restrictions")}.naturalId()
</#if>                    
<#foreach property in pojo.getAllPropertiesIterator()>
<#if property.isNaturalIdentifier()>
                            .set("${property.name}", ${property.name})
</#if>
</#foreach>
                        )
                    .uniqueResult();
            if (instance==null) {
                log.debug("get successful, no instance found");
            }
            else {
                log.debug("get successful, instance found");
            }
            return instance;
        }
        catch (RuntimeException re) {
            log.error("query failed", re);
            throw re;
        }
    }
</#if>    
<#if jdk5>
    public ${pojo.importType("java.util.List")}<${declarationName}> findByExample(${declarationName} instance) {
<#else>
    public ${pojo.importType("java.util.List")} findByExample(${declarationName} instance) {
</#if>
        log.debug("finding ${declarationName} instance by example");
        try {
<#if jdk5>
            ${pojo.importType("java.util.List")}<${declarationName}> results = (List<${declarationName}>) ${DaoUtils}.sessionFactory.getCurrentSession()
<#else>
            ${pojo.importType("java.util.List")} results = ${DaoUtils}.sessionFactory.getCurrentSession()
</#if>
                    .createCriteria("${clazz.entityName}")
<#if jdk5>
                    .add( ${pojo.staticImport("org.hibernate.criterion.Example", "create")}(instance) )
<#else>
                    .add(${pojo.importType("org.hibernate.criterion.Example")}.create(instance))
</#if>
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    } 
<#foreach queryName in cfg.namedQueries.keySet()>
<#if queryName.startsWith(clazz.entityName + ".")>
<#assign methname = c2j.unqualify(queryName)>
<#assign params = cfg.namedQueries.get(queryName).parameterTypes><#assign argList = c2j.asFinderArgumentList(params, pojo)>
<#if jdk5 && methname.startsWith("find")>
    public ${pojo.importType("java.util.List")}<${declarationName}> ${methname}(${argList}) {
<#elseif methname.startsWith("count")>
    public int ${methname}(${argList}) {
<#else>
    public ${pojo.importType("java.util.List")} ${methname}(${argList}) {
</#if>
        ${pojo.importType("org.hibernate.Query")} query = ${DaoUtils}.sessionFactory.getCurrentSession()
                .getNamedQuery("${queryName}");
<#foreach param in params.keySet()>
<#if param.equals("maxResults")>
		query.setMaxResults(maxResults);
<#elseif param.equals("firstResult")>
        query.setFirstResult(firstResult);
<#else>
        query.setParameter("${param}", ${param});
</#if>
</#foreach>
<#if jdk5 && methname.startsWith("find")>
        return (List<${declarationName}>) query.list();
<#elseif methname.startsWith("count")>
        return ( (Integer) query.uniqueResult() ).intValue();
<#else>
        return query.list();
</#if>
    }
</#if>
</#foreach></#if>
}
</#assign>

${pojo.generateImports()}
${classbody}
