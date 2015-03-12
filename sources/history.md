|Дата|Изменение|Пользователь|
|-|-|-|
#exec git log --pretty=format:"|%ci|%s|%an|" | grep '_techdoc_' | sed -r 's/_techdoc_//g' | head -n 10 

