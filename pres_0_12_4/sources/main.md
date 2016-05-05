#ifndef _LANG_
#warning _LANG_ is not defined. 'ru' will be used for _LANG_
#define _LANG_ ru
#endif
#ifndef _SYSTEM_
#error _SYSTEM_ must be specified
#endif
#include title_page.md

#include history.md
#include introduction.md

#ifeq _LANG_ en
# Debug info
#else
# Сводная информация
#endif

#ifeq _LANG_ en
Generation date: #exec date "+%Y-%m-%d %H:%M:%S"
Language: _LANG_
OS: _SYSTEM_
#else
Дата генерации: #exec date "+%Y-%m-%d %H:%M:%S"
Язык генерации: _LANG_
Система: _SYSTEM_
#endif
