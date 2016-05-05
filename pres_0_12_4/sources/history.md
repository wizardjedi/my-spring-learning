#ifeq _LANG_ en
# Change history
#else
# История изменений
#endif

#ifeq _LANG_ en
|Date|Changeset|Author|
#else
|Дата|Изменение|Пользователь|
#endif
|-|-|-|
#exec git log --pretty=format:"|%ci|%s|%an|" | grep '_techdoc_' | sed -r 's/_techdoc_//g' | head -n 10 

#ifeq _LANG_ en
Changeset history generated via GIT commits
#else
История составлена на основании коммитов из GIT
#endif
