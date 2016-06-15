package com.mdy.async.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NodeParser {

	public NodeParser(Node node) {
		_node = node;
	}

	public String getAttributeValue(String attrName) {
		initAttrMap();
		return (String) _attrMap.get(attrName);
	}

	public int getAttributeCount() {
		initAttrMap();
		return _attrMap.size();
	}

	private void initAttrMap() {
		if (null != _attrMap)
			return;
		_attrMap = new HashMap();
		NamedNodeMap nodeMap = _node.getAttributes();
		if (null == nodeMap)
			return;
		for (int i = 0; i < nodeMap.getLength(); i++) {
			Node attr = nodeMap.item(i);
			_attrMap.put(attr.getNodeName(), attr.getNodeValue());
		}

	}

	public List getChildNodes() {
		initChildNodeList();
		return _childNodes;
	}

	public int getChildNodeCount() {
		initChildNodeList();
		return _childNodes.size();
	}

	public Node getChildNode(String nodeName) {
		if (null == nodeName)
			return null;
		initChildNodeList();
		for (Iterator i$ = _childNodes.iterator(); i$.hasNext();) {
			Node node = (Node) i$.next();
			if (nodeName.equals(node.getNodeName()))
				return node;
		}

		return null;
	}

	public String getChildNodeValue(String nodeName) {
		Node node = getChildNode(nodeName);
		if (null == node)
			return null;
		else
			return node.getTextContent();
	}

	private void initChildNodeList() {
		if (null != _childNodes)
			return;
		_childNodes = new ArrayList();
		NodeList nodeList = _node.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (1 == node.getNodeType())
				_childNodes.add(node);
		}

	}

	private Node _node;
	private Map _attrMap;
	private List _childNodes;
}
