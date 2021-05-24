package com.csf.whoami.common.utilities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * �ǉ������ӎ������}�b�v.
 *
 * @author C_KanamoriYasuharu
 */
@SuppressWarnings({"rawtypes", "unchecked", "serial"})
public class ListMap implements Map, Serializable {

    /**
     * �}�b�v
     */
    private Map map = new HashMap();
    /**
     * �L�[�̏�����ێ����郊�X�g
     */
    private List keylist = new ArrayList();

    /**
     * �f�t�H���g�R���X�g���N�^.
     */
    public ListMap() {
        super();
    }

    /**
     * �����l�t���R���X�g���N�^.
     */
    public ListMap(List keylist, Map map) {
        this();
        this.keylist = keylist;
        this.map = map;
    }

    /**
     * �L�[�l�����Ԗڂɒǉ����ꂽ����Ԃ�.
     *
     * @param key �L�[�l
     * @return �ǉ����ꂽindex
     * @see ArrayList#indexOf(java.lang.Object)
     */
    public int indexOfKey(String key) {
        return keylist.indexOf(key);
    }

    /*
     * @see java.util.Map#size()
     */
    public int size() {
        return map.size();
    }

    /*
     * @see java.util.Map#isEmpty()
     */
    public boolean isEmpty() {
        return map.isEmpty();
    }

    /*
     * @see java.util.Map#containsKey(java.lang.Object)
     */
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    /*
     * @see java.util.Map#containsValue(java.lang.Object)
     */
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    /*
     * @see java.util.Map#get(java.lang.Object)
     */
    public Object get(Object key) {
        return map.get(key);
    }

    /**
     * �ȑO�ɒǉ����ꂽ�L�[���ēx�ǉ�����ꍇ�͑O��̏��Ԃ��Ȃ����čēx
     * �ǉ����ꂽ���Ԃŕێ�����B
     * �܂�A
     * �R�Ԗڂɒǉ����ꂽ�L�["A"
     * �S�Ԗڂɒǉ����ꂽ�L�["B"
     * ���̏�ԂłT�ԖڂɃL�["A"��ǉ������
     * �R�Ԗڂ̃L�[��"B"�ɂȂ�A�T�C�Y���S�̂܂܂ł���.
     *
     * @param key
     * @param value
     * @see java.util.Map#put(java.lang.Object, java.lang.Object)
     */
    public Object put(Object key, Object value) {
        if (keylist.contains(key)) {
            keylist.remove(key);
        }
        keylist.add(key);
        return map.put(key, value);
    }

    /* �L�[�̏�����ێ����郊�X�g����L�[���폜���鏈����ǉ������B
     * @see java.util.Map#remove(java.lang.Object)
     */
    public Object remove(Object key) {
        keylist.remove(key);
        return map.remove(key);
    }

    /* �L�[�̏�����ێ����郊�X�g�ɃL�[��ǉ����鏈����ǉ������B
     * @see java.util.Map#putAll(java.util.Map)
     */
    public void putAll(Map t) {
        map.putAll(t);
        keylist.addAll(t.keySet());
    }

    /* �L�[�̏�����ێ����郊�X�g����L�[���폜���鏈����ǉ������B
     * @see java.util.Map#clear()
     */
    public void clear() {
        map.clear();
        keylist.clear();
    }

    /* �L�[�̏�����ێ����郊�X�g��Ԃ��悤�ɂ����B
     * @see java.util.Map#keySet()
     */
    public Set keySet() {
        Set ret = new ListSet(map.keySet());
        return ret;
    }

    /*
     * @see java.util.Map#values()
     */
    public Collection values() {
        Collection ret = new ArrayList();
        int max = keylist.size();
        for (int idx = 0; idx < max; idx++) {
            ret.add(get(keylist.get(idx)));
        }
        return ret;
    }

    /* �L�[�̏�����ێ����郊�X�g����A�G���g���[�̃Z�b�g���č\�z����悤�ɂ����B
     * @see java.util.Map#entrySet()
     */
    public Set entrySet() {
        Set ret = new ListSet(map.entrySet());
        return ret;
    }

    /* �}�b�v�̏��ƃL�[���X�g�̏����o�͂���悤�ɂ����B
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuffer buf = new StringBuffer(super.toString());
        buf.append(" map=").append(map);
        buf.append(" keylist=").append(keylist);
        return buf.toString();
    }

    /**
     * ���X�g�t���Z�b�g�C���^�[�t�F�[�X.
     * ListMap��p.
     *
     * @author C_KanamoriYasuharu
     */
    public class ListSet implements Set {
        private List list;

        public ListSet(Set set) {
            super();
            int max = set.size();
            this.list = new ArrayList(max);
            for (int idx = 0; idx < max; idx++) {
                list.add("");
            }
            Iterator itr = set.iterator();
            while (itr.hasNext()) {
                Object obj = itr.next();
                if (obj instanceof Map.Entry) {        // entrySet����Ă΂ꂽ�ꍇ
                    Map.Entry entry = (Map.Entry) obj;
                    list.set(keylist.indexOf(entry.getKey()), entry);
                } else {                            // keySet����Ă΂ꂽ�ꍇ
                    list.set(keylist.indexOf(obj), obj);
                }
            }
        }

        public Iterator iterator() {
            return list.iterator();
        }

        public Object[] toArray() {
            // ListMap����͎g�p���Ȃ��̂ŁA���b�p�ɂ��邾��
            return list.toArray();
        }

        public Object[] toArray(Object[] a) {
            // ListMap����͎g�p���Ȃ��̂ŁA���b�p�ɂ��邾��
            return list.toArray(a);
        }

        public boolean add(Object o) {
            // ListMap����͎g�p���Ȃ��̂ŁA���b�p�ɂ��邾��
            return list.add(o);
        }

        public boolean remove(Object o) {
            // ListMap����͎g�p���Ȃ��̂ŁA���b�p�ɂ��邾��
            return list.remove(o);
        }

        public boolean addAll(Collection c) {
            // ListMap����͎g�p���Ȃ��̂ŁA���b�p�ɂ��邾��
            return list.addAll(c);
        }

        public boolean retainAll(Collection c) {
            // ListMap����͎g�p���Ȃ��̂ŁA���b�p�ɂ��邾��
            return list.retainAll(c);
        }

        public boolean removeAll(Collection c) {
            // ListMap����͎g�p���Ȃ��̂ŁA���b�p�ɂ��邾��
            return list.removeAll(c);
        }

        public void clear() {
            // ListMap����͎g�p���Ȃ��̂ŁA���b�p�ɂ��邾��
            list.clear();
        }

        public boolean contains(Object o) {
            return list.contains(o);
        }

        public boolean containsAll(Collection c) {
            return list.containsAll(c);
        }

        public boolean isEmpty() {
            return list.isEmpty();
        }

        public int size() {
            return list.size();
        }

    }

}
