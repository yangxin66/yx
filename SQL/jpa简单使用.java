Po类的定义

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name = "dhr_business_enum")
@Entity(name = "Enum")
@SQLDelete(sql = "update Enum set Enum.deleted = 1 where Enum.id = ?")
@Where(clause = "deleted = 0")
public class EnumPo extends BasePo {

    private static final long serialVersionUID = -1426496386150187379L;

    //字段公司id
    @Column(name = "company_id",length = 50)
    private String companyId;

    //  分组id
    @Column(name = "code",length = 20)
    private String code;

    //分组名称
    @Column(name = "code_name" ,length = 50)
    private String codeName;

    @OneToMany
    @JoinColumn(name = "enum_id")
    private List<EnumItemPo> enumItemPoList;
}



@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name = "dhr_business_enum_item")
@Entity(name = "EnumItem")
@SQLDelete(sql = "update Enum set EnumItem.deleted = 1 where EnumItem.id = ?")
@Where(clause = "deleted = 0")
public class EnumItemPo extends BasePo {

    private static final long serialVersionUID = -2272339648956531994L;

    @Column(name = "enum_id")
    private Long enumId;

    //分组id
    @Column(name = "code", length = 20)
    private String code;

    //描述
    @Column(name = "item_des", length = 254)
    private String description;

    //枚举项id
    @Column(name = "item_code", length = 50)
    private String itemCode;

    //  枚举项值
    @Column(name = "item_value", length = 254)
    private String itemValue;

    @Column(name = "pid", length = 20)
    private String pid;   //pid

    //路径
    @Column(name = "item_path", length = 254)
    private String itemPath;

    //层级
    @Column(name = "item_level")
    private Integer itemLevel;
}



注意@OneToMang的使用，这个字段对应的One表的id，而不是自己在One中定义的一个字段。
@Data注解，是Dao层使用的一个实体类的标签，
@Entity的标签说明这个实体对应数据库中的表，
@Table(name="数据库的表名")当实体类的名字跟数据库中表的名字不一样是使用。
@Id下的字段对应数据库中的主键名，
@Column(name="")对应数据库中列名。
一对多关系
@OneToMany(cascade ="", mappedBy = "子表的关联值")//指向多的那方的pojo的关联外键字段  对应的另一个类的属性名称  。

@JoinColumn(name = "ONE_ID", referencedColumnName = "ONE_ID")//设置对应数据表的列名和引用的数据表的列名       
@ManyToOne//设置在“一方”pojo的外键字段上   

多对多
8.多对多映射关系  
貌似多对多关系不需要设置级联，以前用hibernate的时候着实为多对多的级联头疼了一阵子，JPA的多对多还需要实际的尝试一下才能有所体会。  
估计JPA的多对多也是可以转换成两个一对多的。  
  
第一个Pojo    
@Entity       
@Table(name = "T_MANYA")       
public class ManyA implements Serializable {       
private static final long serialVersionUID = 1L;       
@Id       
@Column(name = "MANYA_ID", nullable = false)       
private String manyaId;       
@Column(name = "DESCRIPTION")       
private String description;       
@ManyToMany       
@JoinTable(name = "TMANY1_TMANY2", joinColumns = {@JoinColumn(name = "MANYA_ID", referencedColumnName = "MANYA_ID")}  
private Collection<ManyB> manybIdCollection;       
  
  
第二个Pojo    
@Entity       
@Table(name = "T_MANYB")       
public class ManyB implements Serializable {       
private static final long serialVersionUID = 1L;       
@Id       
@Column(name = "MANYB_ID", nullable = false)       
private String manybId;       
@Column(name = "DESCRIPTION")       
private String description;       
@ManyToMany//(mappedBy = "manybIdCollection")       
private Collection<ManyA> manyaIdCollection;  


ddl-auto属性:

create：每次运行程序时，都会重新创建表，故而数据会丢失
create-drop：每次运行程序时会先创建表结构，然后待程序结束时清空表
upadte：每次运行程序，没有表时会创建表，如果对象发生改变会更新表结构，原有数据不会清空，只会更新（推荐使用）
validate：运行程序会校验数据与数据库的字段类型是否相同，字段不同会报错
none: 禁用DDL处理

主键生成策略：更加uuid生成的
	@Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;
     @GenericGenerator是Hibernate提供的主键生成策略注解，注意@GeneratedValue（JPA注解）
	 使用generator = "idGenerator"引用了上面的name = "idGenerator"主键生成策略

	 
	 一般简单的Demo示例中只会使用@GeneratedValue(strategy = GenerationType.IDENTITY)这种主键自增的策略，而实际数据库中表字段主键类型很少是int型的

	JPA自带的几种主键生成策略

	TABLE： 使用一个特定的数据库表格来保存主键
	SEQUENCE： 根据底层数据库的序列来生成主键，条件是数据库支持序列。这个值要与generator一起使用，generator 指定生成主键使用的生成器（可能是orcale中自己编写的序列）
	IDENTITY： 主键由数据库自动生成（主要是支持自动增长的数据库，如mysql）
	AUTO： 主键由程序控制，也是GenerationType的默认值
	
	
	PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "username"));动态的排序和分组;
	
	@Query注解只是查询，如涉及到修改、删除则需要再加上@Modifying注解