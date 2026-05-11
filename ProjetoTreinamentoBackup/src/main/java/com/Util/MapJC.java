package com.Util;

import java.util.*;

/**
 * @author Guilherme JC - 27-02-2020
 */

public class MapJC extends HashMap {
   public MapJC() {
      super();
   }

   @Override
   public int size() {
      return super.size();
   }

   @Override
   public boolean isEmpty() {
      return super.isEmpty();
   }

   @Override
   public Object get(Object key) {
      return super.get(key);
   }

   @Override
   public boolean containsKey(Object key) {
      return super.containsKey(key);
   }

   @Override
   public MapJC put(Object key, Object value) {
      super.put(key, value);
      return this;
   }

   @Override
   public void putAll(Map m) {
      super.putAll(m);
   }

   @Override
   public Object remove(Object key) {
      return super.remove(key);
   }

   @Override
   public void clear() {
      super.clear();
   }

   @Override
   public boolean containsValue(Object value) {
      return super.containsValue(value);
   }

   @Override
   public Set keySet() {
      return super.keySet();
   }

   @Override
   public Collection values() {
      return super.values();
   }

   @Override
   public Set<Entry> entrySet() {
      return super.entrySet();
   }

   @Override
   public boolean remove(Object key, Object value) {
      return super.remove(key, value);
   }

   @Override
   public Object clone() {
      return super.clone();
   }

   public boolean isEmptyValue(Object value) {
      if (!containsKey(value) || value == null) {
         return true;
      }
      if (get(value) == null) {
         return true;
      }
      if ("".equals(get(value).toString().trim())) {
         return true;
      }
      if ("null".equals(get(value).toString().trim())) {
         return true;
      }
      return false;
   }

   public String getString(Object key) {
      if (isEmptyValue(key)) {
         return null;
      }
      return get(key).toString();
   }


   public Integer getInteger(Object key) {
      if (isEmptyValue(key)) {
         return null;
      }
      if (!jcUtil.isOnlyNumber(get(key).toString())) {
         return null;
      }
      return Integer.valueOf(get(key).toString());
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof MapJC)) return false;
      MapJC that = (MapJC) o;
      return Objects.equals(that.getString("primaryKey"), getString("primaryKey")) ;
   }

   @Override
   public int hashCode() {
      return Objects.hash(getString("primaryKey"), getString("descricao"));
   }

   @Override
   public String toString() {
      return this.getString("primaryKey");
   }
}
